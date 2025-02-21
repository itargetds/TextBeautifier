package me.itargetds.textbeautifier;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StyleUtils {
    private static final boolean IS_MODERN_VERSION = checkVersion();

    public static String applyStyles(String text, String styles) {
        if (text == null || styles == null || text.isEmpty()) {
            return text;
        }

        List<String> styleList = new ArrayList<>(Arrays.asList(styles.toLowerCase().split("\\+")));
        StringBuilder formattedText = new StringBuilder();
        ChatColor color = null;

        for (String style : styleList) {
            if (style.startsWith("hex-")) {
                if (IS_MODERN_VERSION) {
                    String hexCode = style.substring(4);
                    if (isValidHex(hexCode)) {
                        color = ChatColor.of(hexCode);
                    } else {
                        color = ChatColor.WHITE;
                    }
                } else {
                    color = ChatColor.WHITE;
                }
            } else if (style.startsWith("gradient-")) {
                if (IS_MODERN_VERSION) {
                    String[] gradientParts = style.substring(9).split("-");
                    if (gradientParts.length >= 2 && Arrays.stream(gradientParts).allMatch(StyleUtils::isValidHex)) {
                        return applyGradient(text, gradientParts, styleList.subList(1, styleList.size()));
                    } else {
                        return ChatColor.WHITE + text;
                    }
                } else {
                    return ChatColor.WHITE + text;
                }
            } else {
                switch (style.toLowerCase()) {
                    case "black": color = ChatColor.getByChar('0'); break;
                    case "darkblue": color = ChatColor.getByChar('1'); break;
                    case "darkgreen": color = ChatColor.getByChar('2'); break;
                    case "darkaqua": color = ChatColor.getByChar('3'); break;
                    case "darkred": color = ChatColor.getByChar('4'); break;
                    case "purple": color = ChatColor.getByChar('5'); break;
                    case "gold": color = ChatColor.getByChar('6'); break;
                    case "gray": color = ChatColor.getByChar('7'); break;
                    case "darkgray": color = ChatColor.getByChar('8'); break;
                    case "blue": color = ChatColor.getByChar('9'); break;
                    case "green": color = ChatColor.getByChar('a'); break;
                    case "aqua": color = ChatColor.getByChar('b'); break;
                    case "red": color = ChatColor.getByChar('c'); break;
                    case "pink": color = ChatColor.getByChar('d'); break;
                    case "yellow": color = ChatColor.getByChar('e'); break;
                    case "white": color = ChatColor.getByChar('f'); break;
                }
            }

            if (color != null) {
                formattedText.append(color);
                styleList.remove(style);
                break;
            }
        }

        for (String style : styleList) {
            formattedText.append(getStyleCode(style));
        }

        formattedText.append(text);
        return formattedText.toString();
    }

    private static String applyGradient(String text, String[] colors, List<String> extraStyles) {
        if (!IS_MODERN_VERSION) {
            return ChatColor.WHITE + text;
        }

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

    private static boolean isValidHex(String hex) {
        return hex.matches("#[0-9a-fA-F]{6}");
    }

    private static boolean checkVersion() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        int major = Integer.parseInt(parts[1]);
        return major >= 13;
    }
}
