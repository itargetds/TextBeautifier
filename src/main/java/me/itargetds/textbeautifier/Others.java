package me.itargetds.textbeautifier;

import org.bukkit.Bukkit;

import java.util.Arrays;

public class Others {

    public static boolean IS_MODERN_VERSION = true;

    public static boolean isValidHex(String hex) {
        return hex.matches("#[0-9a-fA-F]{6}");
    }

    public static boolean isValidHexArray(String[] hexArray) {
        return Arrays.stream(hexArray).allMatch(Others::isValidHex);
    }

    public static boolean isModernVersion() {
        String[] parts = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
        return Integer.parseInt(parts[1]) >= 16;
    }
}
