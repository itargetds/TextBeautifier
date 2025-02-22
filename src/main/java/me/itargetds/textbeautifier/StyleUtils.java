package me.itargetds.textbeautifier;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StyleUtils {
    private static final boolean IS_MODERN_VERSION = checkVersion();

    public static String applyStyles(String text, String styles) {
        if (text == null || styles == null || text.isEmpty()) return text;

        List<String> styleList = new ArrayList<>(Arrays.asList(styles.toLowerCase().split("\\+")));
        String formattedText = extractColor(styleList, text);
        for (String style : styleList) {
            formattedText += getStyleCode(style);
        }

        return formattedText;
    }

    private static String extractColor(List<String> styleList, String text) {
        for (Iterator<String> iterator = styleList.iterator(); iterator.hasNext(); ) {
            String style = iterator.next();
            if (style.startsWith("gradient-") && IS_MODERN_VERSION) {
                String[] gradientColors = style.substring(9).split("-");
                if (gradientColors.length >= 2 && Arrays.stream(gradientColors).allMatch(StyleUtils::isValidHex)) {
                    iterator.remove();
                    return applyGradient(text, gradientColors, styleList);
                }
            } else if (style.startsWith("hex-") && IS_MODERN_VERSION) {
                String hexCode = style.substring(4);
                if (isValidHex(hexCode)) {
                    iterator.remove();
                    return ChatColor.of(hexCode) + text;
                }
            } else {
                ChatColor color = getColorCode(style);
                if (color != null) {
                    iterator.remove();
                    return color + text;
                }
            }
        }
        return ChatColor.WHITE + text;
    }

    private static String applyGradient(String text, String[] colors, List<String> extraStyles) {
        StringBuilder gradientText = new StringBuilder();
        int length = text.length();
        List<Color> colorList = Arrays.stream(colors).map(Color::decode).collect(Collectors.toList());

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

            for (String style : extraStyles) {
                String styleCode = getStyleCode(style);
                if (!styleCode.isEmpty()) {
                    gradientText.append(styleCode);
                }
            }

            gradientText.append(text.charAt(i));
        }
        return gradientText.toString();
    }

    private static String getStyleCode(String style) {
        switch (style.toLowerCase()) {
            case "bold": return ChatColor.BOLD.toString();
            case "italic": return ChatColor.ITALIC.toString();
            case "underline": return ChatColor.UNDERLINE.toString();
            case "strikethrough": return ChatColor.STRIKETHROUGH.toString();
            case "magic": return ChatColor.MAGIC.toString();
            default: return "";
        }
    }

    private static ChatColor getColorCode(String style) {
        switch (style.toLowerCase()) {
            case "black": return ChatColor.getByChar('0');
            case "darkblue": return ChatColor.getByChar('1');
            case "darkgreen": return ChatColor.getByChar('2');
            case "darkaqua": return ChatColor.getByChar('3');
            case "darkred": return ChatColor.getByChar('4');
            case "purple": return ChatColor.getByChar('5');
            case "gold": return ChatColor.getByChar('6');
            case "gray": return ChatColor.getByChar('7');
            case "darkgray": return ChatColor.getByChar('8');
            case "blue": return ChatColor.getByChar('9');
            case "green": return ChatColor.getByChar('a');
            case "aqua": return ChatColor.getByChar('b');
            case "red": return ChatColor.getByChar('c');
            case "pink": return ChatColor.getByChar('d');
            case "yellow": return ChatColor.getByChar('e');
            case "white": return ChatColor.getByChar('f');
            default: return null;
        }
    }

    private static boolean isValidHex(String hex) {
        return hex.matches("#[0-9a-fA-F]{6}");
    }

    private static boolean checkVersion() {
        String[] parts = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
        return Integer.parseInt(parts[1]) >= 13;
    }
}
