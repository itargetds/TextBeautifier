package me.itargetds.textbeautifier.Font;

import static me.itargetds.textbeautifier.Font.FontChars.*;

public class FontUtils {

    public static String applyFont(String text, String typeFont) {
        for (String font : typeFont.toLowerCase().split("\\+")) {
            switch (font) {

                case "fancy":
                    return convertToCustomFont(text, NORMAL_CHARS, FANCY_CHARS);

                case "circled":
                    return convertToCustomFont(text, NORMAL_CHARS, CIRCLED_CHARS);

                case "superscript":
                    return convertToCustomFont(text, NORMAL_CHARS, SUPERSCRIPT_CHARS);

                case "smallcaps":
                    return convertToCustomFont(text, NORMAL_CHARS_NO_NUMS, SMALLCAPS_CHARS);

            }
        }
        return text;
    }

    private static String convertToCustomFont(String text, String normal, String converted) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = normal.indexOf(c);
            result.append(index != -1 ? converted.charAt(index) : c);
        }
        return result.toString();
    }

}

