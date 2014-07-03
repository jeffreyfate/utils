package com.jeffthefate.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

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

    public ArrayList<String> generateSymbolList() {
        ArrayList<String> symbolList = new ArrayList<>(0);
        symbolList.add("*");
        symbolList.add("+");
        symbolList.add("~");
        symbolList.add("^");
        symbolList.add("§");
        symbolList.add("¤");
        symbolList.add("$");
        symbolList.add("%");
        return symbolList;
    }

    public ArrayList<ArrayList<String>> generateSongMatchList() {
        ArrayList<ArrayList<String>> songList = new
                ArrayList<>(0);
        ArrayList<String> tempList = new ArrayList<>(0);
        tempList.add("belly belly nice");
        tempList.add("bbn");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("the riff");
        tempList.add("riff");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("true");
        tempList.add("true reflections");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dido");
        tempList.add("drive in drive out");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lyd");
        tempList.add("let you down");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("liog");
        tempList.add("lie in our graves");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("proudest");
        tempList.add("pm");
        tempList.add("proudest monkey");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("smts");
        tempList.add("so much to say");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("billies");
        tempList.add("tripping billies");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("byah");
        tempList.add("build you a house");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ggt");
        tempList.add("good good time");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ioy");
        tempList.add("idea of you");
        tempList.add("the idea of you");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ktk");
        tempList.add("kill the king");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lw");
        tempList.add("loving wings");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("suad");
        tempList.add("sweet up and down");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("astb");
        tempList.add("anyone seen the bridge");
        tempList.add("anyone seen the bridge?");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("wwbom");
        tempList.add("what will become of me");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ftwii");
        tempList.add("funny");
        tempList.add("funny the way it is");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lithog");
        tempList.add("lying in the hands of god");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("smlam");
        tempList.add("shake me like a monkey");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("wia");
        tempList.add("why i am");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("you & me");
        tempList.add("you and me");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("bef");
        tempList.add("big eyed fish");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dad");
        tempList.add("digging a ditch");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("gig");
        tempList.add("grace is gone");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("kkj");
        tempList.add("kit kat jam");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("wayg");
        tempList.add("where are you going");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ynk");
        tempList.add("you never know");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ants");
        tempList.add("ants marching");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("bowa");
        tempList.add("best of what's around");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("jimi");
        tempList.add("jimi thing");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lld");
        tempList.add("lover lay down");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("pfwyg");
        tempList.add("pay for what you get");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("rhyme");
        tempList.add("rhyme & reason");
        tempList.add("rhyme and reason");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("typical");
        tempList.add("typical situation");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("wwys");
        tempList.add("what would you say");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dftr");
        tempList.add("reaper");
        tempList.add("the reaper");
        tempList.add("don't fear the reaper");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("afm");
        tempList.add("angel from montgomery");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("bdth");
        tempList.add("burning down the house");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("cortez");
        tempList.add("cortez the killer");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dbtr");
        tempList.add("down by the river");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("fitr");
        tempList.add("fool in the rain");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ftbow");
        tempList.add("for the beauty of wynona");
        tempList.add("beauty of wynona");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("fhtsa");
        tempList.add("funny how time slips away");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("gtbt");
        tempList.add("good times bad times");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("hhmm");
        tempList.add("hey hey my my");
        tempList.add("hey hey my my into the black");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("maker");
        tempList.add("the maker");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lbv");
        tempList.add("long black veil");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("majdbts");
        tempList.add("me and julio down by the schoolyard");
        tempList.add("me and julio");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("needle and the damage down");
        tempList.add("the needle and the damage done");
        tempList.add("tnatdd");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("siu");
        tempList.add("stir it up");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("thank you");
        tempList.add("thank you falettinme be mice elf agin");
        tempList.add("tyfbmea");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("tots");
        tempList.add("time of the season");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("abi");
        tempList.add("american baby intro");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ewu");
        tempList.add("everybody wake up");
        tempList.add("everybody wake up (our finest hour arrives)");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("bayou");
        tempList.add("louisiana bayou");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("odh");
        tempList.add("old dirt hill");
        tempList.add("old dirt hill (bring that beat back)");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("oomh");
        tempList.add("out of my hands");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stand up");
        tempList.add("stand up (for it)");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("steady");
        tempList.add("steady as we go");
        tempList.add("sawg");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stolen");
        tempList.add("stolen away");
        tempList.add("stolen away on 55th & 3rd");
        tempList.add("stolen away on 55th and 3rd");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ymdt");
        tempList.add("die trying");
        tempList.add("you might die trying");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dreams of our fathers");
        tempList.add("doof");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ftt");
        tempList.add("fool to think");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("idi");
        tempList.add("i did it");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("iihia");
        tempList.add("if i had it all");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stdh");
        tempList.add("sleep to dream her");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("tsb");
        tempList.add("the space between");
        tempList.add("space between");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("wya");
        tempList.add("what you are");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("wtwe");
        tempList.add("when the world ends");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ibyu");
        tempList.add("i'll back you up");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("osw");
        tempList.add("one sweet world");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stjl");
        tempList.add("tstjl");
        tempList.add("song that jane likes");
        tempList.add("the song that jane likes");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("loml");
        tempList.add("love of my life");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("gbe");
        tempList.add("grey blue eyes");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("sdl");
        tempList.add("so damn lucky");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stay or leave");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("too high");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("uaa");
        tempList.add("up and away");
        tempList.add("up & away");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("ddtw");
        tempList.add("don't drink the water");
        tempList.add("don't drink");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dreaming tree");
        tempList.add("the dreaming tree");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("the last stop");
        tempList.add("last stop");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("pnp");
        tempList.add("pantala naga pampa");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stay");
        tempList.add("stay (wasting time)");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stone");
        tempList.add("the stone");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("crash");
        tempList.add("crash into me");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("hunger");
        tempList.add("hunger for the great light");
        tempList.add("hftgl");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("nancies");
        tempList.add("dancing nancies");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("corn bread");
        tempList.add("cornbread");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("help myself");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("joyride");
        tempList.add("joy ride");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lrb");
        tempList.add("little red bird");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("write a song");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("watchtower");
        tempList.add("all along the watchtower");
        tempList.add("aatw");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("sugarman");
        tempList.add("sugar man");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dreamgirl");
        tempList.add("dream girl");
        songList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("#36");
        tempList.add("#36 jam");
        tempList.add("36");
        tempList.add("36 jam");
        songList.add(tempList);
        return songList;
    }

    public ArrayList<ArrayList<String>> setupAnswerList() {
        ArrayList<ArrayList<String>> answerList = new
                ArrayList<>(0);
        ArrayList<String> tempList = new ArrayList<>(0);
        tempList.add("dave");
        tempList.add("dave matthews");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("boyd");
        tempList.add("boyd tinsley");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stefan");
        tempList.add("stefan lessard");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("carter");
        tempList.add("carter beauford");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("leroi");
        tempList.add("leroi moore");
        tempList.add("roi");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("butch");
        tempList.add("butch taylor");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("tim");
        tempList.add("tim reynolds");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("jeff");
        tempList.add("jeff coffin");
        tempList.add("coffin");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("rashawn");
        tempList.add("rashawn ross");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lillywhite");
        tempList.add("steve lillywhite");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("lawlor");
        tempList.add("joe lawlor");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("fenton");
        tempList.add("fenton williams");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("peter");
        tempList.add("peter griesar");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("sax");
        tempList.add("saxophone");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("alpine");
        tempList.add("alpine valley");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("big whiskey and the groogrux king");
        tempList.add("big whiskey");
        tempList.add("big whiskey & the groogrux king");
        tempList.add("bwggk");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("stay");
        tempList.add("stay (wasting time)");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("you and me");
        tempList.add("you & me");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("rhyme and reason");
        tempList.add("rhyme & reason");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("funny the way it is");
        tempList.add("ftwii");
        tempList.add("funny");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("sweet up & down");
        tempList.add("sweet up and down");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("billies");
        tempList.add("tripping billies");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("bela fleck and the flecktones");
        tempList.add("bela fleck & the flecktones");
        tempList.add("the flecktones");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("any noise");
        tempList.add("any noise/anti-noise");
        tempList.add("any noise anti-noise");
        tempList.add("any noise anti noise");
        tempList.add("any noise antinoise");
        tempList.add("anynoise antinoise");
        answerList.add(tempList);
        tempList = new ArrayList<>(0);
        tempList.add("dreamgirl");
        tempList.add("dream girl");
        answerList.add(tempList);
        return answerList;
    }

    public HashMap<String, String> createAcronymMap() {
        HashMap<String, String> acronymMap = new HashMap<>();
        acronymMap.put("btcs", "before these crowded streets");
        acronymMap.put("uttad", "under the table and dreaming");
        acronymMap.put("watchtower", "all along the watchtower");
        acronymMap.put("hunger", "hunger for the great light");
        acronymMap.put("crash", "crash into me");
        acronymMap.put("nancies", "dancing nancies");
        acronymMap.put("msg", "madison square garden");
        acronymMap.put("wpb", "west palm beach");
        acronymMap.put("ddtw", "dont drink the water");
        return acronymMap;
    }

    public ArrayList<String> createReplaceList() {
        ArrayList<String> replaceList = new ArrayList<>(0);
        replaceList.add("the ");
        replaceList.add("his ");
        replaceList.add("her ");
        return replaceList;
    }

    public ArrayList<String> createTipList() {
        ArrayList<String> tipList = new ArrayList<>(0);
        tipList.add("Scoring: #1 - Full points, #2 - 3/4 points, " +
                "#3 - 1/2 points\nIf you protect tweets we must follow you to" +
                " play (ask us)");
        tipList.add("You won't see people guess who protect their tweets unless you follow each other");
        tipList.add("Only one guess per person is accepted for each question");
        tipList.add("Note: We have a free DMB Trivia & Setlist app in the Google Play Store https://play.google.com/store/apps/details?id=com.jeffthefate.dmbquiz");
        return tipList;
    }

}
