package com.jeffthefate.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * Created by Jeff on 6/18/2014.
 */
public class GameUtil {

    private static GameUtil gameUtil;

    public static GameUtil instance() {
        if (gameUtil == null) {
            gameUtil = new GameUtil();
        }
        return gameUtil;
    }

    public String massageResponse(String text) {
        return text.toLowerCase(Locale.getDefault()).replaceFirst(
                "(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9]+)",
                "").
                replaceAll("[.,'`\":;/?\\-!@#Ä~+*]", "").trim();
    }

    public String massageAnswer(String text) {
        String answer = text.replaceAll("[.,'`\":;/?\\-!@#Ä~+*]", "").
                toLowerCase(Locale.getDefault()).trim();
        return answer.replaceAll("(5\\|\\|)+", "");
    }

    public boolean checkAnswer(String answer, String response) {
        if (answer == null || response == null)
            return false;
        int buffer = 4;
        switch (answer.length()) {
            case 15:
            case 14:
            case 13:
            case 12:
            case 11:
            case 10:
                buffer = 3;
                break;
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
                buffer = 2;
                break;
            case 4:
            case 3:
                buffer = 1;
                break;
            case 2:
            case 1:
                buffer = 0;
                break;
        }
        if (answer.matches("^[0-9]+$"))
            buffer = 0;
        int diffCount = StringUtils.getLevenshteinDistance(response, answer,
                buffer);

        switch (diffCount) {
            case -1:
                return false;
            default:
                return true;
        }
    }
}
