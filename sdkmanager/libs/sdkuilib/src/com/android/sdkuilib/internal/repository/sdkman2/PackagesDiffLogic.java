/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.sdkuilib.internal.repository.sdkman2;

import com.android.sdklib.IAndroidTarget;
import com.android.sdklib.SdkConstants;
import com.android.sdklib.internal.repository.ExtraPackage;
import com.android.sdklib.internal.repository.IPackageVersion;
import com.android.sdklib.internal.repository.Package;
import com.android.sdklib.internal.repository.PlatformPackage;
import com.android.sdklib.internal.repository.PlatformToolPackage;
import com.android.sdklib.internal.repository.SdkSource;
import com.android.sdklib.internal.repository.SystemImagePackage;
import com.android.sdklib.internal.repository.ToolPackage;
import com.android.sdklib.util.SparseArray;
import com.android.sdkuilib.internal.repository.UpdaterData;
import com.android.sdkuilib.internal.repository.sdkman2.PkgItem.PkgState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Helper class that separates the logic of package management from the UI
 * so that we can test it using head-less unit tests.
 */
class PackagesDiffLogic {
    private final PackageLoader mPackageLoader;
    private final UpdaterData mUpdaterData;
    private boolean mFirstLoadComplete = true;

    public PackagesDiffLogic(UpdaterData updaterData) {
        mUpdaterData = updaterData;
        mPackageLoader = new PackageLoader(updaterData);
    }

    public PackageLoader getPackageLoader() {
        return mPackageLoader;
    }

    /**
     * Removes all the internal state and resets the object.
     * Useful for testing.
     */
    public void clear() {
        mFirstLoadComplete = true;
        mOpApi.clear();
        mOpSource.clear();
    }

    /** Return mFirstLoadComplete and resets it to false.
     * All following calls will returns false. */
    public boolean isFirstLoadComplete() {
        boolean b = mFirstLoadComplete;
        mFirstLoadComplete = false;
        return b;
    }

    /**
     * Mark all new and update PkgItems as checked.
     *
     * @param selectNew If true, select all new packages
     * @param selectUpdates If true, select all update packages
     * @param selectTop If true, select the top platform. If the top platform has nothing installed,
     *   select all items in it; if it is partially installed, at least select the platform and
     *   system images if none of the system images are installed.
     * @param currentPlatform The {@link SdkConstants#currentPlatform()} value.
     */
    public void checkNewUpdateItems(
            boolean selectNew,
            boolean selectUpdates,
            boolean selectTop,
            int currentPlatform) {
        int maxApi = 0;
        Set<Integer> installedPlatforms = new HashSet<Integer>();
        SparseArray<List<PkgItem>> platformItems = new SparseArray<List<PkgItem>>();

        // sort items in platforms... directly deal with new/update items
        for (PkgItem item : getAllPkgItems(true /*byApi*/, true /*bySource*/)) {
            if (!item.hasCompatibleArchive()) {
                // Ignore items that have no archive compatible with the current platform.
                continue;
            }

            // Get the main package's API level. We don't need to look at the updates
            // since by definition they should target the same API level.
            int api = 0;
            Package p = item.getMainPackage();
            if (p instanceof IPackageVersion) {
                api = ((IPackageVersion) p).getVersion().getApiLevel();
            }

            if (selectTop && api > 0) {
                // Keep track of the max api seen
                maxApi = Math.max(maxApi, api);

                // keep track of what platform is currently installed (that is, has at least
                // one thing installed.)
                if (item.getState() == PkgState.INSTALLED) {
                    installedPlatforms.add(api);
                }

                // for each platform, collect all its related item for later use below.
                List<PkgItem> items = platformItems.get(api);
                if (items == null) {
                    platformItems.put(api, items = new ArrayList<PkgItem>());
                }
                items.add(item);
            }

            if ((selectNew && item.getState() == PkgState.NEW) ||
                    (selectUpdates && item.hasUpdatePkg())) {
                item.setChecked(true);
            }
        }

        List<PkgItem> items = platformItems.get(maxApi);
        if (selectTop && maxApi > 0 && items != null) {
            if (!installedPlatforms.contains(maxApi)) {
                // If the top platform has nothing installed at all, select everything in it
                for (PkgItem item : items) {
                    if (item.getState() == PkgState.NEW || item.hasUpdatePkg()) {
                        item.setChecked(true);
                    }
                }

            } else {
                // The top platform has at least one thing installed.

                // First make sure the platform package itself is installed, or select it.
                for (PkgItem item : items) {
                     Package p = item.getMainPackage();
                     if (p instanceof PlatformPackage && item.getState() == PkgState.NEW) {
                         item.setChecked(true);
                         break;
                     }
                }

                // Check we have at least one system image installed, otherwise select them
                boolean hasSysImg = false;
                for (PkgItem item : items) {
                    Package p = item.getMainPackage();
                    if (p instanceof PlatformPackage && item.getState() == PkgState.INSTALLED) {
                        if (item.hasUpdatePkg() && item.isChecked()) {
                            // If the installed platform is schedule for update, look for the
                            // system image in the update package, not the current one.
                            p = item.getUpdatePkg();
                            if (p instanceof PlatformPackage) {
                                hasSysImg = ((PlatformPackage) p).getIncludedAbi() != null;
                            }
                        } else {
                            // Otherwise look into the currently installed platform
                            hasSysImg = ((PlatformPackage) p).getIncludedAbi() != null;
                        }
                        if (hasSysImg) {
                            break;
                        }
                    }
                    if (p instanceof SystemImagePackage && item.getState() == PkgState.INSTALLED) {
                        hasSysImg = true;
                        break;
                    }
                }
                if (!hasSysImg) {
                    // No system image installed.
                    // Try whether the current platform or its update would bring one.

                    for (PkgItem item : items) {
                         Package p = item.getMainPackage();
                         if (p instanceof PlatformPackage) {
                             if (item.getState() == PkgState.NEW &&
                                     ((PlatformPackage) p).getIncludedAbi() != null) {
                                 item.setChecked(true);
                                 hasSysImg = true;
                             } else if (item.hasUpdatePkg()) {
                                 p = item.getUpdatePkg();
                                 if (p instanceof PlatformPackage &&
                                         ((PlatformPackage) p).getIncludedAbi() != null) {
                                     item.setChecked(true);
                                     hasSysImg = true;
                                 }
                             }
                         }
                    }
                }
                if (!hasSysImg) {
                    // No system image in the platform, try a system image package
                    for (PkgItem item : items) {
                        Package p = item.getMainPackage();
                        if (p instanceof SystemImagePackage && item.getState() == PkgState.NEW) {
                            item.setChecked(true);
                        }
                    }
                }
            }
        }

        if (selectTop && currentPlatform == SdkConstants.PLATFORM_WINDOWS) {
            // On Windows, we'll also auto-select the USB driver
            for (PkgItem item : getAllPkgItems(true /*byApi*/, true /*bySource*/)) {
                Package p = item.getMainPackage();
                if (p instanceof ExtraPackage && item.getState() == PkgState.NEW) {
                    ExtraPackage ep = (ExtraPackage) p;
                    if (ep.getVendor().equals("google") &&          //$NON-NLS-1$
                            ep.getPath().equals("usb_driver")) {    //$NON-NLS-1$
                        item.setChecked(true);
                    }
                }
            }
        }
    }

    /**
     * Mark all PkgItems as not checked.
     */
    public void uncheckAllItems() {
        for (PkgItem item : getAllPkgItems(true /*byApi*/, true /*bySource*/)) {
            item.setChecked(false);
        }
    }

    /**
     * An update operation, customized to either sort by API or sort by source.
     */
    abstract class UpdateOp {
        private final Set<SdkSource> mVisitedSources = new HashSet<SdkSource>();
        private final List<PkgCategory> mCategories = new ArrayList<PkgCategory>();
        private final Set<PkgCategory> mCatsToRemove = new HashSet<PkgCategory>();
        private final Set<PkgItem> mItemsToRemove = new HashSet<PkgItem>();
        private final Map<Package, PkgItem> mUpdatesToRemove = new HashMap<Package, PkgItem>();

        /** Removes all internal state. */
        public void clear() {
            mVisitedSources.clear();
            mCategories.clear();
        }

        /** Retrieve the sorted category list. */
        public List<PkgCategory> getCategories() {
            return mCategories;
        }

        /** Retrieve the category key for the given package, either local or remote. */
        public abstract Object getCategoryKey(Package pkg);

        /** Modified {@code currentCategories} to add default categories. */
        public abstract void addDefaultCategories();

        /** Creates the category for the given key and returns it. */
        public abstract PkgCategory createCategory(Object catKey);

        /** Sorts the category list (but not the items within the categories.) */
        public abstract void sortCategoryList();

        /** Called after items of a given category have changed. Used to sort the
         * items and/or adjust the category name. */
        public abstract void postCategoryItemsChanged();

        public void updateStart() {
            mVisitedSources.clear();

            // Note that default categories are created after the unused ones so that
            // the callback can decide whether they should be marked as unused or not.
            mCatsToRemove.clear();
            mItemsToRemove.clear();
            mUpdatesToRemove.clear();
            for (PkgCategory cat : mCategories) {
                mCatsToRemove.add(cat);
                List<PkgItem> items = cat.getItems();
                mItemsToRemove.addAll(items);
                for (PkgItem item : items) {
                    if (item.hasUpdatePkg()) {
                        mUpdatesToRemove.put(item.getUpdatePkg(), item);
                    }
                }
            }

            addDefaultCategories();
        }

        public boolean updateSourcePackages(SdkSource source, Package[] newPackages) {
            if (newPackages.length > 0) {
                mVisitedSources.add(source);
            }
            if (source == null) {
                return processLocals(this, newPackages);
            } else {
                return processSource(this, source, newPackages);
            }
        }

        public boolean updateEnd() {
            boolean hasChanged = false;

            // Remove unused categories & items at the end of the update
            synchronized (mCategories) {
                for (PkgCategory unusedCat : mCatsToRemove) {
                    if (mCategories.remove(unusedCat)) {
                        hasChanged  = true;
                    }
                }
            }

            for (PkgCategory cat : mCategories) {
                for (Iterator<PkgItem> itemIt = cat.getItems().iterator(); itemIt.hasNext(); ) {
                    PkgItem item = itemIt.next();
                    if (mItemsToRemove.contains(item)) {
                        itemIt.remove();
                    } else if (item.hasUpdatePkg() &&
                            mUpdatesToRemove.containsKey(item.getUpdatePkg())) {
                        item.removeUpdate();
                    }
                }
            }

            mCatsToRemove.clear();
            mItemsToRemove.clear();
            mUpdatesToRemove.clear();

            return hasChanged;
        }

        public boolean isKeep(PkgItem item) {
            return !mItemsToRemove.contains(item);
        }

        public void keep(Package pkg) {
            mUpdatesToRemove.remove(pkg);
        }

        public void keep(PkgItem item) {
            mItemsToRemove.remove(item);
        }

        public void keep(PkgCategory cat) {
            mCatsToRemove.remove(cat);
        }

        public void dontKeep(PkgItem item) {
            mItemsToRemove.add(item);
        }

        public void dontKeep(PkgCategory cat) {
            mCatsToRemove.add(cat);
        }
    }

    private final UpdateOpApi    mOpApi    = new UpdateOpApi();
    private final UpdateOpSource mOpSource = new UpdateOpSource();

    public List<PkgCategory> getCategories(boolean displayIsSortByApi) {
        return displayIsSortByApi ? mOpApi.getCategories() : mOpSource.getCategories();
    }

    public List<PkgItem> getAllPkgItems(boolean byApi, boolean bySource) {
        List<PkgItem> items = new ArrayList<PkgItem>();

        if (byApi) {
            List<PkgCategory> cats = getCategories(true /*displayIsSortByApi*/);
            synchronized (cats) {
                for (PkgCategory cat : cats) {
                    items.addAll(cat.getItems());
                }
            }
        }

        if (bySource) {
            List<PkgCategory> cats = getCategories(false /*displayIsSortByApi*/);
            synchronized (cats) {
                for (PkgCategory cat : cats) {
                    items.addAll(cat.getItems());
                }
            }
        }

        return items;
    }

    public void updateStart() {
        mOpApi.updateStart();
        mOpSource.updateStart();
    }

    public boolean updateSourcePackages(
            boolean displayIsSortByApi,
            SdkSource source,
            Package[] newPackages) {

        boolean apiListChanged = mOpApi.updateSourcePackages(source, newPackages);
        boolean sourceListChanged = mOpSource.updateSourcePackages(source, newPackages);
        return displayIsSortByApi ? apiListChanged : sourceListChanged;
    }

    public boolean updateEnd(boolean displayIsSortByApi) {
        boolean apiListChanged = mOpApi.updateEnd();
        boolean sourceListChanged = mOpSource.updateEnd();
        return displayIsSortByApi ? apiListChanged : sourceListChanged;
    }


    /** Process all local packages. Returns true if something changed. */
    private boolean processLocals(UpdateOp op, Package[] packages) {
        boolean hasChanged = false;
        List<PkgCategory> cats = op.getCategories();
        Set<PkgItem> keep = new HashSet<PkgItem>();

        // For all locally installed packages, check they are either listed
        // as installed or create new installed items for them.

        nextPkg: for (Package localPkg : packages) {
            // Check to see if we already have the exact same package
            // (type & revision) marked as installed.
            for (PkgCategory cat : cats) {
                for (PkgItem currItem : cat.getItems()) {
                    if (currItem.getState() == PkgState.INSTALLED &&
                            currItem.isSameMainPackageAs(localPkg)) {
                        // This package is already listed as installed.
                        op.keep(currItem);
                        op.keep(cat);
                        keep.add(currItem);
                        continue nextPkg;
                    }
                }
            }

            // If not found, create a new installed package item
            keep.add(addNewItem(op, localPkg, PkgState.INSTALLED));
            hasChanged = true;
        }

        // Remove installed items that we don't want to keep anymore. They would normally be
        // cleanup up in UpdateOp.updateEnd(); however it's easier to remove them before we
        // run processSource() to avoid merging updates in items that would be removed later.

        for (PkgCategory cat : cats) {
            for (Iterator<PkgItem> itemIt = cat.getItems().iterator(); itemIt.hasNext(); ) {
                PkgItem item = itemIt.next();
                if (item.getState() == PkgState.INSTALLED && !keep.contains(item)) {
                    itemIt.remove();
                    hasChanged = true;
                }
            }
        }

        if (hasChanged) {
            op.postCategoryItemsChanged();
        }

        return hasChanged;
    }

    /** Process all remote packages. Returns true if something changed. */
    private boolean processSource(UpdateOp op, SdkSource source, Package[] packages) {
        boolean hasChanged = false;
        List<PkgCategory> cats = op.getCategories();

        nextPkg: for (Package newPkg : packages) {
            for (PkgCategory cat : cats) {
                for (PkgState state : PkgState.values()) {
                    for (Iterator<PkgItem> currItemIt = cat.getItems().iterator();
                                           currItemIt.hasNext(); ) {
                        PkgItem currItem = currItemIt.next();
                        // We need to merge with installed items first. When installing
                        // the diff will have both the new and the installed item and we
                        // need to merge with the installed one before the new one.
                        if (currItem.getState() != state) {
                            continue;
                        }
                        // Only process current items if they represent the same item (but
                        // with a different revision number) than the new package.
                        Package mainPkg = currItem.getMainPackage();
                        if (!mainPkg.sameItemAs(newPkg)) {
                            continue;
                        }

                        // Check to see if we already have the exact same package
                        // (type & revision) marked as main or update package.
                        if (currItem.isSameMainPackageAs(newPkg)) {
                            op.keep(currItem);
                            op.keep(cat);
                            continue nextPkg;
                        } else if (currItem.hasUpdatePkg() &&
                                currItem.isSameUpdatePackageAs(newPkg)) {
                            op.keep(currItem.getUpdatePkg());
                            op.keep(cat);
                            continue nextPkg;
                        }

                        switch (currItem.getState()) {
                        case NEW:
                            if (newPkg.getRevision() < mainPkg.getRevision()) {
                                if (!op.isKeep(currItem)) {
                                    // The new item has a lower revision than the current one,
                                    // but the current one hasn't been marked as being kept so
                                    // it's ok to downgrade it.
                                    currItemIt.remove();
                                    addNewItem(op, newPkg, PkgState.NEW);
                                    hasChanged = true;
                                }
                            } else if (newPkg.getRevision() > mainPkg.getRevision()) {
                                // We have a more recent new version, remove the current one
                                // and replace by a new one
                                currItemIt.remove();
                                addNewItem(op, newPkg, PkgState.NEW);
                                hasChanged = true;
                            }
                            break;
                        case INSTALLED:
                            // if newPkg.revision <= mainPkg.revision: it's already installed, ignore.
                            if (newPkg.getRevision() > mainPkg.getRevision()) {
                                // This is a new update for the main package.
                                if (currItem.mergeUpdate(newPkg)) {
                                    op.keep(currItem.getUpdatePkg());
                                    op.keep(cat);
                                    hasChanged = true;
                                }
                            }
                            break;
                        }
                        continue nextPkg;
                    }
                }
            }
            // If not found, create a new package item
            addNewItem(op, newPkg, PkgState.NEW);
            hasChanged = true;
        }

        if (hasChanged) {
            op.postCategoryItemsChanged();
        }

        return hasChanged;
    }

    private PkgItem addNewItem(UpdateOp op, Package pkg, PkgState state) {
        List<PkgCategory> cats = op.getCategories();
        Object catKey = op.getCategoryKey(pkg);
        PkgCategory cat = findCurrentCategory(cats, catKey);

        if (cat == null) {
            // This is a new category. Create it and add it to the list.
            cat = op.createCategory(catKey);
            synchronized (cats) {
                cats.add(cat);
            }
            op.sortCategoryList();
        }

        PkgItem item = new PkgItem(pkg, state);
        op.keep(item);
        cat.getItems().add(item);
        op.keep(cat);
        return item;
    }

    private PkgCategory findCurrentCategory(
            List<PkgCategory> currentCategories,
            Object categoryKey) {
        for (PkgCategory cat : currentCategories) {
            if (cat.getKey().equals(categoryKey)) {
                return cat;
            }
        }
        return null;
    }

    /**
     * {@link UpdateOp} describing the Sort-by-API operation.
     */
    private class UpdateOpApi extends UpdateOp {
        @Override
        public Object getCategoryKey(Package pkg) {
            // Sort by API

            if (pkg instanceof IPackageVersion) {
                return ((IPackageVersion) pkg).getVersion().getApiLevel();

            } else if (pkg instanceof ToolPackage || pkg instanceof PlatformToolPackage) {
                return PkgCategoryApi.KEY_TOOLS;

            } else {
                return PkgCategoryApi.KEY_EXTRA;
            }
        }

        @Override
        public void addDefaultCategories() {
            boolean needTools = true;
            boolean needExtras = true;

            List<PkgCategory> cats = getCategories();
            for (PkgCategory cat : cats) {
                if (cat.getKey().equals(PkgCategoryApi.KEY_TOOLS)) {
                    // Mark them as no unused to prevent their removal in updateEnd().
                    keep(cat);
                    needTools = false;
                } else if (cat.getKey().equals(PkgCategoryApi.KEY_EXTRA)) {
                    keep(cat);
                    needExtras = false;
                }
            }

            // Always add the tools & extras categories, even if empty (unlikely anyway)
            if (needTools) {
                PkgCategoryApi acat = new PkgCategoryApi(
                        PkgCategoryApi.KEY_TOOLS,
                        null,
                        mUpdaterData.getImageFactory().getImageByName(PackagesPage.ICON_CAT_OTHER));
                synchronized (cats) {
                    cats.add(acat);
                }
            }

            if (needExtras) {
                PkgCategoryApi acat = new PkgCategoryApi(
                        PkgCategoryApi.KEY_EXTRA,
                        null,
                        mUpdaterData.getImageFactory().getImageByName(PackagesPage.ICON_CAT_OTHER));
                synchronized (cats) {
                    cats.add(acat);
                }
            }
        }

        @Override
        public PkgCategory createCategory(Object catKey) {
            // Create API category.
            PkgCategory cat = null;

            assert catKey instanceof Integer;
            int apiKey = ((Integer) catKey).intValue();

            // We need a label for the category.
            // If we have an API level, try to get the info from the SDK Manager.
            // If we don't (e.g. when installing a new platform that isn't yet available
            // locally in the SDK Manager), it's OK we'll try to find the first platform
            // package available.
            String platformName = null;
            if (apiKey >= 1 && apiKey != PkgCategoryApi.KEY_TOOLS) {
                for (IAndroidTarget target :
                        mUpdaterData.getSdkManager().getTargets()) {
                    if (target.isPlatform() &&
                            target.getVersion().getApiLevel() == apiKey) {
                        platformName = target.getVersionName();
                        break;
                    }
                }
            }

            cat = new PkgCategoryApi(
                    apiKey,
                    platformName,
                    mUpdaterData.getImageFactory().getImageByName(PackagesPage.ICON_CAT_PLATFORM));

            return cat;
        }

        @Override
        public void sortCategoryList() {
            // Sort the categories list.
            // We always want categories in order tools..platforms..extras.
            // For platform, we compare in descending order (o2-o1).
            // This order is achieved by having the category keys ordered as
            // needed for the sort to just do what we expect.

            synchronized (getCategories()) {
                Collections.sort(getCategories(), new Comparator<PkgCategory>() {
                    public int compare(PkgCategory cat1, PkgCategory cat2) {
                        assert cat1 instanceof PkgCategoryApi;
                        assert cat2 instanceof PkgCategoryApi;
                        int api1 = ((Integer) cat1.getKey()).intValue();
                        int api2 = ((Integer) cat2.getKey()).intValue();
                        return api2 - api1;
                    }
                });
            }
        }

        @Override
        public void postCategoryItemsChanged() {
            // Sort the items
            for (PkgCategory cat : getCategories()) {
                Collections.sort(cat.getItems());

                // When sorting by API, we can't always get the platform name
                // from the package manager. In this case at the very end we
                // look for a potential platform package we can use to extract
                // the platform version name (e.g. '1.5') from the first suitable
                // platform package we can find.

                assert cat instanceof PkgCategoryApi;
                PkgCategoryApi pac = (PkgCategoryApi) cat;
                if (pac.getPlatformName() == null) {
                    // Check whether we can get the actual platform version name (e.g. "1.5")
                    // from the first Platform package we find in this category.

                    for (PkgItem item : cat.getItems()) {
                        Package p = item.getMainPackage();
                        if (p instanceof PlatformPackage) {
                            String platformName = ((PlatformPackage) p).getVersionName();
                            if (platformName != null) {
                                pac.setPlatformName(platformName);
                                break;
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * {@link UpdateOp} describing the Sort-by-Source operation.
     */
    private class UpdateOpSource extends UpdateOp {
        @Override
        public Object getCategoryKey(Package pkg) {
            // Sort by source
            SdkSource source = pkg.getParentSource();
            if (source == null) {
                return PkgCategorySource.UNKNOWN_SOURCE;
            }
            return source;
        }

        @Override
        public void addDefaultCategories() {
            List<PkgCategory> cats = getCategories();
            for (PkgCategory cat : cats) {
                if (cat.getKey().equals(PkgCategorySource.UNKNOWN_SOURCE)) {
                    // Already present.
                    return;
                }
            }

            // Always add the local categories, even if empty (unlikely anyway)
            PkgCategorySource cat = new PkgCategorySource(
                    PkgCategorySource.UNKNOWN_SOURCE,
                    mUpdaterData);
            // Mark it so that it can be cleared in updateEnd() if not used.
            dontKeep(cat);
            synchronized (cats) {
                cats.add(cat);
            }
        }

        @Override
        public PkgCategory createCategory(Object catKey) {
            assert catKey instanceof SdkSource;
            PkgCategory cat = new PkgCategorySource((SdkSource) catKey, mUpdaterData);
            return cat;

        }

        @Override
        public void sortCategoryList() {
            // Sort the sources in ascending source name order,
            // with the local packages always first.

            synchronized (getCategories()) {
                Collections.sort(getCategories(), new Comparator<PkgCategory>() {
                    public int compare(PkgCategory cat1, PkgCategory cat2) {
                        assert cat1 instanceof PkgCategorySource;
                        assert cat2 instanceof PkgCategorySource;

                        SdkSource src1 = ((PkgCategorySource) cat1).getSource();
                        SdkSource src2 = ((PkgCategorySource) cat2).getSource();

                        if (src1 == src2) {
                            return 0;
                        } else if (src1 == PkgCategorySource.UNKNOWN_SOURCE) {
                            return -1;
                        } else if (src2 == PkgCategorySource.UNKNOWN_SOURCE) {
                            return 1;
                        }
                        assert src1 != null; // true because LOCAL_SOURCE==null
                        assert src2 != null;
                        return src1.toString().compareTo(src2.toString());
                    }
                });
            }
        }

        @Override
        public void postCategoryItemsChanged() {
            // Sort the items
            for (PkgCategory cat : getCategories()) {
                Collections.sort(cat.getItems());
            }
        }
    }
}
