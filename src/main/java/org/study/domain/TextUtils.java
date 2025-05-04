package org.study.domain;

public class TextUtils {

    public static int getLineNumber(String text, int position) {
        int lineNumber = 1;
        for (int i = 0; i < position; i++) {
            if (text.charAt(i) == '\n') {
                lineNumber++;
            }
        }
        return lineNumber;
    }
}
