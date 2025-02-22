package me.itargetds.textbeautifier;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TextBeautifier extends PlaceholderExpansion {

    private static final Map<String, String> cache = new HashMap<>();

    @Override
    public @NotNull String getIdentifier() {
        return "textbeautifier";
    }

    @Override
    public @NotNull String getAuthor() {
        return "itargetds";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.5";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        String[] arguments = params.split("_", 3);
        if(arguments.length >= 2) {
            String type = arguments[0];
            String subtypes = arguments[1];
            String text = arguments.length > 2 ? arguments[2] : "";
            return formatText(player, type, subtypes, text);
        }
        return null;
    }

    public static String formatText(Player player, String type, String subtypes, String text) {
        if (text == null) return null;

        String cacheKey = type + ":" + subtypes + ":" + text;
        if (cache.containsKey(cacheKey)) return cache.get(cacheKey);

        List<String> types = Arrays.asList(type.toLowerCase().split("\\+"));
        String formattedText = text;

        if (types.contains("placeholder")) {
            formattedText = PlaceholderAPI.setBracketPlaceholders(player, formattedText);
        }

        boolean hasFont = types.contains("font");
        boolean hasStyle = types.contains("style");

        if (hasFont) formattedText = FontUtils.applyFont(formattedText, subtypes);
        if (hasStyle) formattedText = StyleUtils.applyStyles(formattedText, subtypes);

        formattedText = ChatColor.RESET + formattedText + ChatColor.RESET;
        cache.put(cacheKey, formattedText);

        return formattedText;
    }
}
