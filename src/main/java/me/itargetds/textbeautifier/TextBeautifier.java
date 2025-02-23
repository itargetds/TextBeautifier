package me.itargetds.textbeautifier;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.itargetds.textbeautifier.Font.FontUtils;
import me.itargetds.textbeautifier.Style.StyleUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static me.itargetds.textbeautifier.Others.*;

public final class TextBeautifier extends PlaceholderExpansion {

    public static final Map<String, String> cache = new HashMap<>();

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
        return "1.6";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean register() {
        if(!isModernVersion()){
            IS_MODERN_VERSION = false;
            PlaceholderAPIPlugin.getInstance().getLogger().log(
                    Level.WARNING,
                    "Your server is running on a version lower than 1.16. HEX colors and Gradient will not work!"
            );
        }
        return super.register();
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
        if (text == null){
            return null;
        }

        String cacheKey = type + ":" + subtypes + ":" + text;
        if (cache.containsKey(cacheKey)){
            return ChatColor.RESET + cache.get(cacheKey) + ChatColor.RESET;
        }

        List<String> types = Arrays.asList(type.toLowerCase().split("\\+"));
        String formattedText = text;

        if (types.contains("placeholder")) {
            formattedText = PlaceholderAPI.setBracketPlaceholders(player, formattedText);
        }

        if (types.contains("font")) {
            formattedText = FontUtils.applyFont(formattedText, subtypes);
        }

        if (types.contains("style")) {
            formattedText = StyleUtils.applyStyles(formattedText, subtypes);
        }

        cache.put(cacheKey, formattedText);

        return ChatColor.RESET + formattedText + ChatColor.RESET;
    }
}
