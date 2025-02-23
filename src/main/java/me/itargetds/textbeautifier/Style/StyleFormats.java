package me.itargetds.textbeautifier.Style;

import net.md_5.bungee.api.ChatColor;

public class StyleFormats {

    public static ChatColor getColorCode(String style) {
        switch (style.toLowerCase()) {
            case "black":
                return ChatColor.BLACK;
            case "darkblue":
                return ChatColor.DARK_BLUE;
            case "darkgreen":
                return ChatColor.DARK_GREEN;
            case "darkaqua":
                return ChatColor.DARK_AQUA;
            case "darkred":
                return ChatColor.DARK_RED;
            case "purple":
                return ChatColor.DARK_PURPLE;
            case "gold":
                return ChatColor.GOLD;
            case "gray":
                return ChatColor.GRAY;
            case "darkgray":
                return ChatColor.DARK_GRAY;
            case "blue":
                return ChatColor.BLUE;
            case "green":
                return ChatColor.GREEN;
            case "aqua":
                return ChatColor.AQUA;
            case "red":
                return ChatColor.RED;
            case "pink":
                return ChatColor.LIGHT_PURPLE;
            case "yellow":
                return ChatColor.YELLOW;
            case "white":
                return ChatColor.WHITE;
            default: return null;
        }
    }

    public static String getStyleCode(String style) {
        switch (style.toLowerCase()) {
            case "bold":
                return ChatColor.BOLD.toString();
            case "italic":
                return ChatColor.ITALIC.toString();
            case "underline":
                return ChatColor.UNDERLINE.toString();
            case "strikethrough":
                return ChatColor.STRIKETHROUGH.toString();
            case "magic":
                return ChatColor.MAGIC.toString();
            default: return "";
        }
    }

}
