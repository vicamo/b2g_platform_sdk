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

package com.android.sdkuilib.internal.repository;

import com.android.sdklib.SdkManager;
import com.android.sdklib.internal.repository.Archive;
import com.android.sdklib.internal.repository.Package;
import com.android.sdklib.internal.repository.Archive.Arch;
import com.android.sdklib.internal.repository.Archive.Os;

import java.io.File;
import java.util.Properties;

class MockEmptyPackage extends Package {
    private final String mTestHandle;

    public MockEmptyPackage(String testHandle) {
        super(
            null /*source*/,
            null /*props*/,
            0 /*revision*/,
            null /*license*/,
            null /*description*/,
            null /*descUrl*/,
            Os.ANY /*archiveOs*/,
            Arch.ANY /*archiveArch*/,
            null /*archiveOsPath*/
            );
        mTestHandle = testHandle;
    }

    public MockEmptyPackage(String testHandle, int revision) {
        super(
            null /*source*/,
            null /*props*/,
            revision,
            null /*license*/,
            null /*description*/,
            null /*descUrl*/,
            Os.ANY /*archiveOs*/,
            Arch.ANY /*archiveArch*/,
            null /*archiveOsPath*/
            );
        mTestHandle = testHandle;
    }

    @Override
    protected Archive createLocalArchive(
            Properties props,
            Os archiveOs,
            Arch archiveArch,
            String archiveOsPath) {
        return new Archive(this, props, archiveOs, archiveArch, archiveOsPath) {
            @Override
            public String toString() {
                return mTestHandle;
            }
        };
    }

    public Archive getLocalArchive() {
        return getArchives()[0];
    }

    @Override
    public File getInstallFolder(String osSdkRoot, SdkManager sdkManager) {
        return null;
    }

    @Override
    public String getListDescription() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getShortDescription() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        if (getRevision() > 0) {
            sb.append(" rev=").append(getRevision());
        }
        return sb.toString();
    }

    @Override
    public boolean sameItemAs(Package pkg) {
        return (pkg instanceof MockEmptyPackage) &&
            mTestHandle.equals(((MockEmptyPackage) pkg).mTestHandle);
    }

}