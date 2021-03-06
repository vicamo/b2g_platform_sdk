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

import com.android.tools.lint.detector.api.Detector;

@SuppressWarnings("javadoc")
public class IconDetectorTest extends AbstractCheckTest {
    @Override
    protected Detector getDetector() {
        return new IconDetector();
    }

    public void test() throws Exception {
        assertEquals(
            "Warning: Missing density variation folders in res: drawable-xhdpi\n" +
            "drawable-hdpi: Warning: Missing the following drawables in drawable-hdpi: sample_icon.gif (found in drawable-mdpi)\n" +
            "drawable/ic_launcher.png: Warning: The ic_launcher.png icon has identical contents in the following configuration folders: drawable-mdpi, drawable\n" +
            "ic_launcher.png: Warning: Found bitmap drawable res/drawable/ic_launcher.png in densityless folder\n" +
            "sample_icon.gif: Warning: Using the .gif format for bitmaps is discouraged",
            lintProject(
                    "res/drawable/ic_launcher.png",
                    "res/drawable/ic_launcher.png=>res/drawable-mdpi/ic_launcher.png",
                    "res/drawable-mdpi/sample_icon.gif",
                    // Make a dummy file named .svn to make sure it doesn't get seen as
                    // an icon name
                    "res/drawable-mdpi/sample_icon.gif=>res/drawable-hdpi/.svn",
                    "res/drawable-hdpi/ic_launcher.png"));
    }

    public void test2() throws Exception {
        assertEquals(
            "Warning: Missing density variation folders in res: drawable-mdpi, drawable-xhdpi\n" +
            "drawable-hdpi/other.9.png: Warning: The following unrelated icon files have identical contents: appwidget_bg.9.png, other.9.png\n" +
            "drawable-hdpi/unrelated.png: Warning: The following unrelated icon files have identical contents: ic_launcher.png, unrelated.png",
            lintProject(
                    "res/drawable-hdpi/unrelated.png",
                    "res/drawable-hdpi/appwidget_bg.9.png",
                    "res/drawable-hdpi/appwidget_bg_focus.9.png",
                    "res/drawable-hdpi/other.9.png",
                    "res/drawable-hdpi/ic_launcher.png"
                    ));
    }

    public void testNoDpi() throws Exception {
        assertEquals(
            "Warning: Missing density variation folders in res: drawable-hdpi, drawable-xhdpi\n" +
            "drawable-xlarge-nodpi-v11/frame.png: Warning: The frame.png icon has identical contents in the following configuration folders: drawable-mdpi, drawable-nodpi, drawable-xlarge-nodpi-v11\n" +
            "frame.png: Warning: The following images appear in both -nodpi and in a density folder: frame.png",
            lintProject(
                "res/drawable-mdpi/frame.png",
                "res/drawable-nodpi/frame.png",
                "res/drawable-xlarge-nodpi-v11/frame.png"));
    }

    public void testNoDpi2() throws Exception {
        // Having additional icon names in the no-dpi folder should not cause any complaints
        assertEquals(
            "drawable-xhdpi/frame.png: Warning: The following unrelated icon files have identical contents: frame.png, frame.png, frame.png, file1.png, file2.png, frame.png\n" +
            "drawable-xhdpi/frame.png: Warning: The image frame.png varies significantly in its density-independent (dip) size across the various density versions: drawable-ldpi/frame.png: 629x387 dp (472x290 px), drawable-mdpi/frame.png: 472x290 dp (472x290 px), drawable-hdpi/frame.png: 315x193 dp (472x290 px), drawable-xhdpi/frame.png: 236x145 dp (472x290 px)",

            lintProject(
                    "res/drawable-mdpi/frame.png=>res/drawable-mdpi/frame.png",
                    "res/drawable-mdpi/frame.png=>res/drawable-hdpi/frame.png",
                    "res/drawable-mdpi/frame.png=>res/drawable-ldpi/frame.png",
                    "res/drawable-mdpi/frame.png=>res/drawable-xhdpi/frame.png",
                    "res/drawable-mdpi/frame.png=>res/drawable-nodpi/file1.png",
                    "res/drawable-mdpi/frame.png=>res/drawable-nodpi/file2.png"));
    }
}