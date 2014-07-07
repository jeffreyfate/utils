package com.jeffthefate.utils;

import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.parse.Credential;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import org.apache.log4j.Logger;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

public class CredentialUtil {

    private static CredentialUtil credentialUtil;
    private static FileUtil fileUtil;
    private static JsonUtil jsonUtil;

    private Logger logger = Logger.getLogger(CredentialUtil.class);

    public static CredentialUtil instance() {
        if (credentialUtil == null) {
            credentialUtil = new CredentialUtil();
        }
        if (fileUtil == null) {
            fileUtil = FileUtil.instance();
        }
        if (jsonUtil == null) {
            jsonUtil = JsonUtil.instance();
        }
        return credentialUtil;
    }

    public Parse getCredentialedParse(boolean isDev, String credFile) {
        HashMap<Object, Object> credentialsMap = fileUtil.readHashMapFromFile(
                credFile);
        try {
            return new Parse(isDev ? credentialsMap.get("devAppId") :
                    credentialsMap.get("prodAppId"), isDev ?
                    credentialsMap.get("devRestKey") :
                    credentialsMap.get("prodRestKey"));
        } catch (InputMismatchException e) {
            logger.error("A parameter was something other than a string!", e);
            return null;
        }
    }

    public OAuthConsumer getCredentialedOAuth(boolean isDev, String credFile) {
        Parse parse = getCredentialedParse(isDev, credFile);
        String response = parse.get("Credential", "");
        List<Credential> credentialList = jsonUtil.getCredentialResults(
                response).getResults();
        String PLACEFINDER_KEY = null;
        String PLACEFINDER_SECRET = null;
        for (Credential credential : credentialList) {
            switch(credential.getName()) {
                case "placefinderKey":
                    PLACEFINDER_KEY = credential.getValue();
                    break;
                case "placefinderSecret":
                    PLACEFINDER_SECRET = credential.getValue();
                    break;
            }
        }
        if (PLACEFINDER_KEY == null || PLACEFINDER_SECRET == null) {
            return null;
        }
        return new DefaultOAuthConsumer(PLACEFINDER_KEY, PLACEFINDER_SECRET);
    }

    public Configuration getCredentialedTwitter(Parse parse, boolean isGame) {
        String SETLIST_KEY = null;
        String SETLIST_SECRET = null;
        String SETLIST_ACCESS_TOKEN = null;
        String SETLIST_ACCESS_SECRET = null;
        String SETLIST_ACCOUNT = null;
        String GAME_KEY = null;
        String GAME_SECRET = null;
        String GAME_ACCESS_TOKEN = null;
        String GAME_ACCESS_SECRET = null;
        String GAME_ACCOUNT = null;
        String response = parse.get("Credential", "");
        List<Credential> credentialList = jsonUtil.getCredentialResults(
                response).getResults();
        for (Credential credential : credentialList) {
            switch(credential.getName()) {
                case "setlistKey":
                    SETLIST_KEY = credential.getValue();
                    break;
                case "setlistSecret":
                    SETLIST_SECRET = credential.getValue();
                    break;
                case "setlistAccessToken":
                    SETLIST_ACCESS_TOKEN = credential.getValue();
                    break;
                case "setlistAccessSecret":
                    SETLIST_ACCESS_SECRET = credential.getValue();
                    break;
                case "setlistAccount":
                    SETLIST_ACCOUNT = credential.getValue();
                    break;
                case "gameKey":
                    GAME_KEY = credential.getValue();
                    break;
                case "gameSecret":
                    GAME_SECRET = credential.getValue();
                    break;
                case "gameAccessToken":
                    GAME_ACCESS_TOKEN = credential.getValue();
                    break;
                case "gameAccessSecret":
                    GAME_ACCESS_SECRET = credential.getValue();
                    break;
                case "gameAccount":
                    GAME_ACCOUNT = credential.getValue();
                    break;
            }
        }
        if (SETLIST_KEY != null && SETLIST_SECRET != null &&
                SETLIST_ACCESS_TOKEN != null && SETLIST_ACCESS_SECRET != null
                && GAME_KEY != null && GAME_SECRET != null &&
                GAME_ACCESS_TOKEN != null && GAME_ACCESS_SECRET != null) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(isGame ? GAME_KEY : SETLIST_KEY)
                    .setOAuthConsumerSecret(isGame ? GAME_SECRET : SETLIST_SECRET)
                    .setOAuthAccessToken(isGame ? GAME_ACCESS_TOKEN :
                            SETLIST_ACCESS_TOKEN)
                    .setOAuthAccessTokenSecret(isGame ? GAME_ACCESS_SECRET :
                            SETLIST_ACCESS_SECRET);
            return cb.build();
        }
        else {
            return null;
        }
    }

}
