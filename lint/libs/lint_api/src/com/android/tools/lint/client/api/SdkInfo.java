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

package com.android.tools.lint.client.api;

/** Information about SDKs */
public abstract class SdkInfo {
    /**
     * Returns true if the given child view is the same class or a sub class of
     * the given parent view class
     *
     * @param parentViewFqcn the fully qualified class name of the parent view
     * @param childViewFqcn the fully qualified class name of the child view
     * @return true if the child view is a sub view of (or the same class as)
     *         the parent view
     */
    public boolean isSubViewOf(String parentViewFqcn, String childViewFqcn) {
        while (!childViewFqcn.equals("android.view.View")) { //$NON-NLS-1$
            if (parentViewFqcn.equals(childViewFqcn)) {
                return true;
            }
            childViewFqcn = getParentViewClass(childViewFqcn);
            if (childViewFqcn == null) {
                // Unknown view - err on the side of caution
                return true;
            }
        }

        return false;
    }


    /**
     * Returns the fully qualified name of the parent view, or null if the view
     * is the root android.view.View class.
     *
     * @param fqcn the fully qualified class name of the view
     * @return the fully qualified class name of the parent view, or null
     */
    public abstract String getParentViewClass(String fqcn);

    /**
     * Returns the class name of the parent view, or null if the view is the
     * root android.view.View class. This is the same as the
     * {@link #getParentViewClass(String)} but without the package.
     *
     * @param name the view class name to look up the parent for (not including
     *            package)
     * @return the view name of the parent
     */
    public abstract String getParentViewName(String name);

    // TODO: Add access to resource resolution here.
}
