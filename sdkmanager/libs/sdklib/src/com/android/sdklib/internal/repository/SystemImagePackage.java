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

package com.android.sdklib.internal.repository;

import com.android.annotations.VisibleForTesting;
import com.android.annotations.VisibleForTesting.Visibility;
import com.android.sdklib.AndroidVersion;
import com.android.sdklib.SdkConstants;
import com.android.sdklib.SdkManager;
import com.android.sdklib.SystemImage;
import com.android.sdklib.internal.repository.Archive.Arch;
import com.android.sdklib.internal.repository.Archive.Os;
import com.android.sdklib.repository.SdkRepoConstants;

import org.w3c.dom.Node;

import java.io.File;
import java.util.Map;
import java.util.Properties;

/**
 * Represents a system-image XML node in an SDK repository.
 */
public class SystemImagePackage extends Package
        implements IPackageVersion, IPlatformDependency {

    @VisibleForTesting(visibility=Visibility.PRIVATE)
    static final String PROP_ABI = "SystemImage.Abi";      //$NON-NLS-1$

    /** The package version, for platform, add-on and doc packages. */
    private final AndroidVersion mVersion;

    /** The ABI of the system-image. Must not be null nor empty. */
    private final String mAbi;

    /**
     * Creates a new system-image package from the attributes and elements of the given XML node.
     * This constructor should throw an exception if the package cannot be created.
     *
     * @param source The {@link SdkSource} where this is loaded from.
     * @param packageNode The XML element being parsed.
     * @param nsUri The namespace URI of the originating XML document, to be able to deal with
     *          parameters that vary according to the originating XML schema.
     * @param licenses The licenses loaded from the XML originating document.
     */
    SystemImagePackage(SdkSource source,
            Node packageNode,
            String nsUri,
            Map<String,String> licenses) {
        super(source, packageNode, nsUri, licenses);

        int apiLevel = XmlParserUtils.getXmlInt(packageNode, SdkRepoConstants.NODE_API_LEVEL, 0);
        String codeName = XmlParserUtils.getXmlString(packageNode, SdkRepoConstants.NODE_CODENAME);
        if (codeName.length() == 0) {
            codeName = null;
        }
        mVersion = new AndroidVersion(apiLevel, codeName);

        mAbi = XmlParserUtils.getXmlString(packageNode, SdkRepoConstants.NODE_ABI);
    }

    @VisibleForTesting(visibility=Visibility.PRIVATE)
    protected SystemImagePackage(
            AndroidVersion platformVersion,
            int revision,
            String abi,
            Properties props) {
        this(null /*source*/, platformVersion, revision, abi, props);
    }

    @VisibleForTesting(visibility=Visibility.PRIVATE)
    protected SystemImagePackage(
            SdkSource source,
            AndroidVersion platformVersion,
            int revision,
            String abi,
            Properties props) {
        super(  source,                     //source
                props,                      //properties
                revision,                   //revision
                null,                       //license
                null,                       //description
                null,                       //descUrl
                Os.getCurrentOs(),          //archiveOs
                Arch.getCurrentArch(),      //archiveArch
                null                        //archiveOsPath
                );
        mVersion = platformVersion;
        if (abi == null && props != null) {
            abi = props.getProperty(PROP_ABI);
        }
        assert abi != null : "To use this SystemImagePackage constructor you must pass an ABI as a parameter or as a PROP_ABI property";
        mAbi = abi;
    }

    /**
     * Creates a {@link BrokenPackage} representing a system image that failed to load
     * with the regular {@link SdkManager} workflow.
     *
     * @param abiDir The SDK/system-images/android-N/abi folder
     * @param props The properties located in {@code abiDir} or null if not found.
     * @return A new {@link BrokenPackage} that represents this installed package.
     */
    public static Package createBroken(File abiDir, Properties props) {

        String abiType = abiDir.getName();

        StringBuilder sb = new StringBuilder(String.format("Broken %1$s System Image",
                getAbiDisplayNameInternal(abiType)));

        int apiLevel = IExactApiLevelDependency.API_LEVEL_INVALID;
        try {
            // Try to parse the first number out of the platform folder name.
            String platform = abiDir.getParentFile().getName();
            platform = platform.replaceAll("[^0-9]+", " ").trim();     //$NON-NLS-1$ //$NON-NLS-2$
            int pos = platform.indexOf(' ');
            if (pos >= 0) {
                platform = platform.substring(0, pos);
            }

            apiLevel = Integer.parseInt(platform);

            sb.append(String.format(", API %1$d", apiLevel));
        } catch (Exception ignore) {
        }

        String shortDesc = sb.toString();

        return new BrokenPackage(props, shortDesc, shortDesc /*longDesc*/,
                IMinApiLevelDependency.MIN_API_LEVEL_NOT_SPECIFIED,
                apiLevel,
                abiDir.getAbsolutePath());
    }

    /**
     * Save the properties of the current packages in the given {@link Properties} object.
     * These properties will later be given to a constructor that takes a {@link Properties} object.
     */
    @Override
    void saveProperties(Properties props) {
        super.saveProperties(props);

        mVersion.saveProperties(props);
        props.setProperty(PROP_ABI, mAbi);
    }

    /** Returns the ABI of the system-image. Cannot be null nor empty. */
    public String getAbi() {
        return mAbi;
    }

    /** Returns a display-friendly name for the ABI of the system-image. */
    public String getAbiDisplayName() {
        return getAbiDisplayNameInternal(mAbi);
    }

    private static String getAbiDisplayNameInternal(String abi) {
        return abi.replace("armeabi", "ARM EABI")         //$NON-NLS-1$  //$NON-NLS-2$
                  .replace("x86",     "Intel x86 Atom")   //$NON-NLS-1$  //$NON-NLS-2$
                  .replace("-", " ");                     //$NON-NLS-1$  //$NON-NLS-2$
    }

    /**
     * Returns the version of the platform dependency of this package.
     * <p/>
     * A system-image has the same {@link AndroidVersion} as the platform it depends on.
     */
    public AndroidVersion getVersion() {
        return mVersion;
    }

    /**
     * Returns a description of this package that is suitable for a list display.
     * <p/>
     * {@inheritDoc}
     */
    @Override
    public String getListDescription() {
        return String.format("%1$s System Image%2$s",
                getAbiDisplayName(),
                isObsolete() ? " (Obsolete)" : "");
    }

    /**
     * Returns a short description for an {@link IDescription}.
     */
    @Override
    public String getShortDescription() {
        return String.format("%1$s System Image, Android API %2$s, revision %3$s%4$s",
                getAbiDisplayName(),
                mVersion.getApiString(),
                getRevision(),
                isObsolete() ? " (Obsolete)" : "");
    }

    /**
     * Returns a long description for an {@link IDescription}.
     *
     * The long description is whatever the XML contains for the {@code description} field,
     * or the short description if the former is empty.
     */
    @Override
    public String getLongDescription() {
        String s = getDescription();
        if (s == null || s.length() == 0) {
            s = getShortDescription();
        }

        if (s.indexOf("revision") == -1) {
            s += String.format("\nRevision %1$d%2$s",
                    getRevision(),
                    isObsolete() ? " (Obsolete)" : "");
        }

        s += String.format("\nRequires SDK Platform Android API %1$s",
                mVersion.getApiString());
        return s;
    }

    /**
     * Computes a potential installation folder if an archive of this package were
     * to be installed right away in the given SDK root.
     * <p/>
     * A system-image package is typically installed in SDK/systems/platform/abi.
     * The name needs to be sanitized to be acceptable as a directory name.
     *
     * @param osSdkRoot The OS path of the SDK root folder.
     * @param sdkManager An existing SDK manager to list current platforms and addons.
     * @return A new {@link File} corresponding to the directory to use to install this package.
     */
    @Override
    public File getInstallFolder(String osSdkRoot, SdkManager sdkManager) {
        File folder = new File(osSdkRoot, SdkConstants.FD_SYSTEM_IMAGES);
        folder = new File(folder, SystemImage.ANDROID_PREFIX + mVersion.getApiString());

        // Computes a folder directory using the sanitized abi string.
        String abi = mAbi;
        abi = abi.toLowerCase();
        abi = abi.replaceAll("[^a-z0-9_-]+", "_");      //$NON-NLS-1$ //$NON-NLS-2$
        abi = abi.replaceAll("_+", "_");                //$NON-NLS-1$ //$NON-NLS-2$

        folder = new File(folder, abi);
        return folder;
    }

    @Override
    public boolean sameItemAs(Package pkg) {
        if (pkg instanceof SystemImagePackage) {
            SystemImagePackage newPkg = (SystemImagePackage)pkg;

            // check they are the same system-image.
            return getAbi().equals(newPkg.getAbi()) &&
                    getVersion().equals(newPkg.getVersion());
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((mAbi == null) ? 0 : mAbi.hashCode());
        result = prime * result + ((mVersion == null) ? 0 : mVersion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof SystemImagePackage)) {
            return false;
        }
        SystemImagePackage other = (SystemImagePackage) obj;
        if (mAbi == null) {
            if (other.mAbi != null) {
                return false;
            }
        } else if (!mAbi.equals(other.mAbi)) {
            return false;
        }
        if (mVersion == null) {
            if (other.mVersion != null) {
                return false;
            }
        } else if (!mVersion.equals(other.mVersion)) {
            return false;
        }
        return true;
    }
}