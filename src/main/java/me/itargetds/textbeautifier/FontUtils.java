package me.itargetds.textbeautifier;

public class FontUtils {

    public static String applyFont(String text, String typeFont) {
        for (String font : typeFont.toLowerCase().split("\\+")) {
            switch (font) {
                case "fancy": return convert(text, NORMAL_CHARS, FULL_WIDTH_CHARS);
                case "circled": return convert(text, NORMAL_CHARS, CIRCLED_CHARS);
                case "superscript": return convert(text, NORMAL_CHARS, SUPERSCRIPT_CHARS);
                case "smallcaps": return convert(text, NORMAL_CAPS, SMALL_CAPS);
            }
        }
        return text;
    }

    private static String convert(String text, String normal, String converted) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = normal.indexOf(c);
            result.append(index != -1 ? converted.charAt(index) : c);
        }
        return result.toString();
    }

    private static final String NORMAL_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String FULL_WIDTH_CHARS = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ１２３４５６７８９０";
    private static final String CIRCLED_CHARS = "ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩⒶⒷⒸⒹⒺⒻⒼⒽⒾⒿⓀⓁⓂⓃⓄⓅⓆⓇⓈⓉⓊⓋⓌⓍⓎⓏ①②③④⑤⑥⑦⑧⑨⓪";
    private static final String SUPERSCRIPT_CHARS = "ᵃᵇᶜᵈᵉᶠᵍʰⁱʲᵏˡᵐⁿᵒᵖqʳˢᵗᵘᵛʷˣʸᶻᴬᴮᶜᴰᴱᶠᴳᴴᴵᴶᴷᴸᴹᴺᴼᴾQᴿˢᵀᵁⱽᵂˣʸᶻ¹²³⁴⁵⁶⁷⁸⁹⁰";
    private static final String NORMAL_CAPS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SMALL_CAPS = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ";
}

