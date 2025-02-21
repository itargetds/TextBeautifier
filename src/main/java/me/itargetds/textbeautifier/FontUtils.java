package me.itargetds.textbeautifier;

public class FontUtils {

    public static String applyFont(String text, String typeFont) {
        String[] fonts = typeFont.toLowerCase().split("\\+");
        for (String font : fonts) {
            switch (font.toLowerCase()) {
                case "fancy": return convertToFullWidth(text);
                case "circled": return convertToCircled(text);
                case "superscript": return convertToSuperscript(text);
                case "smallcaps": return convertToSmallCaps(text);
            }
        }
        return text;
    }

    private static String convertToFullWidth(String text) {
        StringBuilder result = new StringBuilder();
        String fullWidthChars = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ１２３４５６７８９０";
        String normalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (char c : text.toCharArray()) {
            int index = normalChars.indexOf(c);
            if (index != -1) {
                result.append(fullWidthChars.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String convertToCircled(String text) {
        StringBuilder result = new StringBuilder();
        String circledChars = "ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩⒶⒷⒸⒹⒺⒻⒼⒽⒾⒿⓀⓁⓂⓃⓄⓅⓆⓇⓈⓉⓊⓋⓌⓍⓎⓏ①②③④⑤⑥⑦⑧⑨⓪";
        String normalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (char c : text.toCharArray()) {
            int index = normalChars.indexOf(c);
            if (index != -1) {
                result.append(circledChars.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    //private static String convertToBubble(String text) {
        //    StringBuilder result = new StringBuilder();
        //    String bubbledChars = "🅐🅑🅒🅓🅔🅕🅖🅗🅘🅙🅚🅛🅜🅝🅞🅟🅠🅡🅢🅣🅤🅥🅦🅧🅨🅩🅐🅑🅒🅓🅔🅕🅖🅗🅘🅙🅚🅛🅜🅝🅞🅟🅠🅡🅢🅣🅤🅥🅦🅧🅨🅩❶❷❸❹❺❻❼❽❾⓿";
        //    String normalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        //    for (char c : text.toCharArray()) {
            //        int index = normalChars.indexOf(c);
            //        if (index != -1) {
                //            result.append(bubbledChars.charAt(index));
                //        } else {
                //            result.append(c);
                //        }
            //    }
        //    return result.toString();
        //}

    //private static String convertToUpsideDown(String text) {
    //    StringBuilder result = new StringBuilder();
    //    String upsideDownedChars = "ɐqɔpǝɟƃɥı̣ɾ̣ʞןɯuodbɹsʇnʌʍxʎzⱯꓭƆꓷƎℲꓨHIſꓘꓶWNOԀꝹꓤSꓕꓵꓥMX⅄Z1234567890";
    //    String normalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    //    for (char c : text.toCharArray()) {
    //        int index = normalChars.indexOf(c);
    //        if (index != -1) {
    //            result.append(upsideDownedChars.charAt(index));
    //        } else {
    //            result.append(c);
    //        }
    //    }
    //    return result.toString();
    //}


    private static String convertToSuperscript(String text) {
        StringBuilder result = new StringBuilder();
        String superScriptedChars = "ᵃᵇᶜᵈᵉᶠᵍʰⁱʲᵏˡᵐⁿᵒᵖqʳˢᵗᵘᵛʷˣʸᶻᴬᴮᶜᴰᴱᶠᴳᴴᴵᴶᴷᴸᴹᴺᴼᴾQᴿˢᵀᵁⱽᵂˣʸᶻ¹²³⁴⁵⁶⁷⁸⁹⁰";
        String normalChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (char c : text.toCharArray()) {
            int index = normalChars.indexOf(c);
            if (index != -1) {
                result.append(superScriptedChars.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String convertToSmallCaps(String text) {
        String smallCaps = "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴘǫʀꜱᴛᴜᴠᴡxʏᴢ";
        String normalCaps = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = normalCaps.indexOf(c);
            if (index != -1) {
                result.append(smallCaps.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
