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
public class DuplicateIdDetectorTest extends AbstractCheckTest {
    @Override
    protected Detector getDetector() {
        return new DuplicateIdDetector();
    }

    public void testDuplicate() throws Exception {
        assertEquals(
                "layout/duplicate.xml:5: Warning: Duplicate id @+id/android_logo, already defined earlier in this layout\n" +
                "=> layout/duplicate.xml:4: @+id/android_logo originally defined here",
                lintFiles("res/layout/duplicate.xml"));
    }

    public void testDuplicateChains() throws Exception {
        assertEquals(
            "layout/layout2.xml: Warning: Duplicate id @+id/button1, already defined in layout layout4.xml which is included in this layout\n" +
            "=> layout/layout4.xml: @+id/button1 originally defined here\n" +
            "layout1.xml: Warning: Duplicate id @+id/button1, already defined in layout layout2 which is included in this layout (layout1 => layout3 => layout2)\n" +
            "layout1.xml: Warning: Duplicate id @+id/button2, already defined in layout layout2 which is included in this layout (layout1 => layout4 => layout2)",

            lintProject("res/layout/layout1.xml", "res/layout/layout2.xml",
                        "res/layout/layout3.xml", "res/layout/layout4.xml"));
    }

}
