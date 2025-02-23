package me.itargetds.textbeautifier.Style;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static me.itargetds.textbeautifier.Style.StyleFormats.*;
import static me.itargetds.textbeautifier.Others.*;

public class StyleUtils {

    public static String applyStyles(String text, String styles) {
        if (text == null || styles == null || text.isEmpty()) {
            return text;
        }

        List<String> styleList = new ArrayList<>(Arrays.asList(styles.toLowerCase().split("\\+")));
        StringBuilder formattedText = new StringBuilder();

        String colorizedText = extractColor(styleList, text);

        formattedText.append(colorizedText);

        return formattedText.toString();
    }

    private static String extractColor(List<String> styleList, String text) {
        Iterator<String> iterator = styleList.iterator();
        StringBuilder formattedText = new StringBuilder();

        while (iterator.hasNext()) {
            String style = iterator.next();

            if (style.startsWith("gradient-") && IS_MODERN_VERSION) {
                String[] gradientColors = style.substring(9).split("-");
                if (gradientColors.length >= 2 && isValidHexArray(gradientColors)) {
                    iterator.remove();
                    return applyGradient(text, gradientColors, styleList);
                }
            } else if (style.startsWith("hex-") && IS_MODERN_VERSION) {
                String hexCode = style.substring(4);
                if (isValidHex(hexCode)) {
                    iterator.remove();
                    formattedText.append(ChatColor.of(hexCode));
                }
            } else {
                ChatColor color = getColorCode(style);
                if (color != null) {
                    iterator.remove();
                    formattedText.append(color);
                    for (String extraStyle : styleList) {
                        formattedText.append(getStyleCode(extraStyle));
                    }
                }
            }
        }
        formattedText.append(text);
        return formattedText.toString();
    }

    static String applyGradient(String text, String[] colors, List<String> extraStyles) {
        StringBuilder gradientText = new StringBuilder();
        int length = text.length();
        List<Color> colorList = Arrays.stream(colors)
                .map(Color::decode)
                .collect(Collectors.toList());

        for (int i = 0; i < length; i++) {
            float ratio = (float) i / (length - 1);
            int index = (int) (ratio * (colorList.size() - 1));
            Color start = colorList.get(index);
            Color end = colorList.get(Math.min(index + 1, colorList.size() - 1));
            float localRatio = (ratio * (colorList.size() - 1)) - index;
            int r = (int) (start.getRed() + localRatio * (end.getRed() - start.getRed()));
            int g = (int) (start.getGreen() + localRatio * (end.getGreen() - start.getGreen()));
            int b = (int) (start.getBlue() + localRatio * (end.getBlue() - start.getBlue()));
            ChatColor gradientColor = ChatColor.of(new Color(r, g, b));
            gradientText.append(gradientColor);

            extraStyles.forEach(style -> gradientText.append(getStyleCode(style)));

            gradientText.append(text.charAt(i));
        }
        return gradientText.toString();
    }

}

