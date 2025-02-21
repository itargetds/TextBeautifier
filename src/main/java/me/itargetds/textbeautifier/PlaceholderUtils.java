package me.itargetds.textbeautifier;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderUtils {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{(.*?)}");

    public static String replacePlaceholders(Player player, String input) {
        if (input == null) {
            return input;
        }

        Matcher matcher = PLACEHOLDER_PATTERN.matcher(input);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            String parsedPlaceholder = PlaceholderAPI.setPlaceholders(player, "%" + placeholder + "%");
            if (!parsedPlaceholder.equals("%" + placeholder + "%")) {
                matcher.appendReplacement(result, parsedPlaceholder);
            }
        }

        matcher.appendTail(result);
        return result.toString();
    }

}
