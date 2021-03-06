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

package com.android.tools.lint;

import com.android.tools.lint.detector.api.Location;
import com.android.tools.lint.detector.api.Position;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/** A reporter which emits lint warnings as plain text strings */
class TextReporter extends Reporter {
    private Main mClient;

    TextReporter(Main client, Writer writer) {
        super(writer);
        mClient = client;
    }

    @Override
    void write(int errorCount, int warningCount, List<Warning> issues) throws IOException {
        StringBuilder output = new StringBuilder(issues.size() * 200);
        if (issues.size() == 0) {
            System.out.println("No issues found.");
        } else {
            for (Warning warning : issues) {
                int startLength = output.length();

                if (warning.path != null) {
                    output.append(warning.path);
                    output.append(':');

                    if (warning.line >= 0) {
                        output.append(Integer.toString(warning.line + 1));
                        output.append(':');
                    }
                    if (startLength < output.length()) {
                        output.append(' ');
                    }
                }

                output.append(warning.severity.getDescription());
                output.append(':');
                output.append(' ');

                output.append(warning.message);
                if (warning.issue != null) {
                    output.append(' ').append('[');
                    output.append(warning.issue.getId());
                    output.append(']');
                }

                output.append('\n');

                if (warning.errorLine != null) {
                    output.append(warning.errorLine);
                }

                if (warning.location != null && warning.location.getSecondary() != null) {
                    Location location = warning.location.getSecondary();
                    int count = 0;
                    while (location != null) {
                        if (location.getMessage() != null && location.getMessage().length() > 0) {
                            String path = mClient.getDisplayPath(warning.project,
                                    location.getFile());
                            output.append(path);
                            output.append(':');

                            Position start = location.getStart();
                            if (start != null) {
                                int line = start.getLine();
                                if (line >= 0) {
                                    output.append(Integer.toString(line + 1));
                                    output.append(':');
                                }
                            }

                            output.append(' ');
                            output.append(location.getMessage());
                            output.append('\n');
                            count++;
                            if (count == 5) {
                                output.append("...");
                                output.append('\n');
                            }
                        }

                        location = location.getSecondary();
                    }
                }
            }

            System.out.println(output.toString());

            System.out.println(String.format("%1$d errors, %2$d warnings",
                    errorCount, warningCount));
        }
    }
}