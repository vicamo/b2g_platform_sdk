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

package com.android.tools.lint.checks;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.Speed;

import java.io.File;
import java.util.EnumSet;

/**
 * Check which looks for errors in Proguard files.
 */
public class ProguardDetector extends Detector {

    /** The main issue discovered by this detector */
    public static final Issue ISSUE = Issue.create(
            "Proguard", //$NON-NLS-1$
            "Looks for problems in proguard.cfg files",
            "Using -keepclasseswithmembernames in a proguard.cfg file is not " +
            "correct; it can cause some symbols to be renamed which should not be.\n" +
            "Earlier versions of ADT used to create proguard.cfg files with the " +
            "wrong format. Instead of -keepclasseswithmembernames use " +
            "-keepclasseswithmembers, since the old flags also implies " +
            "\"allow shrinking\" which means symbols only referred to from XML and " +
            "not Java (such as possibly CustomViews) can get deleted.",
            Category.CORRECTNESS,
            8,
            Severity.ERROR,
            ProguardDetector.class,
            EnumSet.of(Scope.PROGUARD)).setMoreInfo(
            "http://http://code.google.com/p/android/issues/detail?id=16384"); //$NON-NLS-1$

    @Override
    public void run(Context context) {
        String contents = context.getContents();
        if (contents != null) {
            int index = contents.indexOf(
                    // Old pattern:
                    "-keepclasseswithmembernames class * {\n" + //$NON-NLS-1$
                    "    public <init>(android.");              //$NON-NLS-1$
            if (index != -1) {
                context.report(ISSUE,
                    Location.create(context.file, contents, index, index),
                    "Obsolete proguard file; use -keepclasseswithmembers instead of -keepclasseswithmembernames", null);
            }
        }
    }

    @Override
    public boolean appliesTo(Context context, File file) {
        return file.getName().equals("proguard.cfg"); //$NON-NLS-1$
    }

    @Override
    public Speed getSpeed() {
        return Speed.FAST;
    }
}
