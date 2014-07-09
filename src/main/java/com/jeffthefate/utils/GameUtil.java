package com.jeffthefate.utils;

import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.parse.Match;
import com.jeffthefate.utils.json.parse.Replace;
import com.jeffthefate.utils.json.parse.Symbol;
import com.jeffthefate.utils.json.parse.Tip;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import twitter4j.conf.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GameUtil {

    private static GameUtil gameUtil;
    private static FileUtil fileUtil;
    private static TwitterUtil twitterUtil;
    private static CredentialUtil credentialUtil;
    private static JsonUtil jsonUtil;

    private Logger logger = Logger.getLogger(GameUtil.class);

    public static GameUtil instance() {
        if (gameUtil == null) {
            gameUtil = new GameUtil();
        }
        if (fileUtil == null) {
            fileUtil = FileUtil.instance();
        }
        if (twitterUtil == null) {
            twitterUtil = TwitterUtil.instance();
        }
        if (credentialUtil == null) {
            credentialUtil = CredentialUtil.instance();
        }
        if (jsonUtil == null) {
            jsonUtil = JsonUtil.instance();
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

    public ArrayList<String> generateSymbolList(boolean isDev,
            String credFile) {
        Parse parse = credentialUtil.getCredentialedParse(isDev, credFile);
        List<Symbol> symbols = jsonUtil.getSymbolResults(parse.get("Symbol",
                "")).getResults();
        ArrayList<String> symbolList = new ArrayList<>(0);
        for (Symbol symbol : symbols) {
            symbolList.add(symbol.getSymbol());
        }
        return symbolList;
    }

    public ArrayList<ArrayList<String>> generateSongMatchList(boolean isDev,
            String credFile) {
        ArrayList<ArrayList<String>> songList = new ArrayList<>(0);
        Parse parse = credentialUtil.getCredentialedParse(isDev, credFile);
        List<Match> matchList = jsonUtil.getMatchResults(parse.get(
                "SongMatch", "")).getResults();
        for (Match match : matchList) {
            songList.add((ArrayList<String>)match.getMatch());
        }
        return songList;
    }

    public ArrayList<ArrayList<String>> setupAnswerList(boolean isDev,
            String credFile) {
        ArrayList<ArrayList<String>> answerList = new
                ArrayList<>(0);
        Parse parse = credentialUtil.getCredentialedParse(isDev, credFile);
        List<Match> matchList = jsonUtil.getMatchResults(parse.get(
                "TriviaMatch", "")).getResults();
        for (Match match : matchList) {
            answerList.add((ArrayList<String>)match.getMatch());
        }
        return answerList;
    }

    public ArrayList<String> createReplaceList(boolean isDev, String credFile) {
        ArrayList<String> replaceList = new ArrayList<>(0);
        Parse parse = credentialUtil.getCredentialedParse(isDev, credFile);
        List<Replace> replaces = jsonUtil.getReplaceResults(parse.get(
                "Replace", "")).getResults();
        for (Replace replace : replaces) {
            replaceList.add(replace.getReplace());
        }
        return replaceList;
    }

    public ArrayList<String> createTriviaTipList(boolean isDev,
            String credFile) {
        ArrayList<String> tipList = new ArrayList<>(0);
        Parse parse = credentialUtil.getCredentialedParse(isDev, credFile);
        List<Tip> tips = jsonUtil.getTipResults(parse.get("TriviaTip", ""))
                .getResults();
        for (Tip tip : tips) {
            tipList.add(tip.getTip());
        }
        return tipList;
    }

    public Date convertStringToDate(String format, String dateString) {
        if (format == null || dateString == null) {
            return null;
        }
        dateString = dateString.replace('_', ':');
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e2) {
            logger.info("Failed to parse date from " + dateString);
            e2.printStackTrace();
        }
        return date;
    }

    public String convertDateFormat(String fromFormat, String toFormat,
            String dateString) {
        if (fromFormat == null || toFormat == null || dateString == null) {
            return null;
        }
        Date date = convertStringToDate(fromFormat, dateString);
        SimpleDateFormat dateFormat = new SimpleDateFormat(toFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date.getTime());
    }

    public boolean saveScores(String scoresFile,
            HashMap<Object, Object> usersMap, Configuration twitterConfig) {
        if (!fileUtil.saveHashMapToFile(scoresFile, usersMap)) {
            twitterUtil.sendDirectMessage(twitterConfig, "jeffthefate",
                    "Failed saving scores!");
            return false;
        }
        return true;
    }

    public HashMap<Object, Object> readScores(String scoresFile) {
        return fileUtil.readHashMapFromFile(scoresFile);
    }

}
