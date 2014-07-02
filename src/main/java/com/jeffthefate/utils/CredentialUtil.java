package com.jeffthefate.utils;

import com.jeffthefate.utils.json.Credential;
import com.jeffthefate.utils.json.JsonUtil;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CredentialUtil {

    private final String CREDENTIAL_FILE = System.getProperty("user.dir") +
            File.separator + "parseCreds";

    private static CredentialUtil credentialUtil;
    private static FileUtil fileUtil;
    private static JsonUtil jsonUtil;

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

    public Parse getCredentialedParse(boolean isDev) {
        HashMap<String, String> credentialsMap = fileUtil.readHashMapFromFile(
                CREDENTIAL_FILE);
        if (credentialsMap.isEmpty()) {
            credentialsMap.put("devAppId",
                    "6pJz1oVHAwZ7tfOuvHfQCRz6AVKZzg1itFVfzx2q");
            credentialsMap.put("devRestKey",
                    "uNZMDvDSahtRxZVRwpUVwzAG9JdLzx4cbYnhYPi7");
            credentialsMap.put("prodAppId",
                    "ImI8mt1EM3NhZNRqYZOyQpNSwlfsswW73mHsZV3R");
            credentialsMap.put("prodRestKey",
                    "1smRSlfAvbFg4AsDxat1yZ3xknHQbyhzZ4msAi5w");
            fileUtil.saveHashMapToFile(CREDENTIAL_FILE, credentialsMap);
        }
        return new Parse(isDev ? credentialsMap.get("devAppId") :
                credentialsMap.get("prodAppId"), isDev ?
                credentialsMap.get("devRestKey") :
                credentialsMap.get("prodAppId"));
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
            if (credential.getName().equals("setlistKey")) {
                SETLIST_KEY = credential.getValue();
            }
            else if (credential.getName().equals("setlistSecret")) {
                SETLIST_SECRET = credential.getValue();
            }
            else if (credential.getName().equals("setlistAccessToken")) {
                SETLIST_ACCESS_TOKEN = credential.getValue();
            }
            else if (credential.getName().equals("setlistAccessSecret")) {
                SETLIST_ACCESS_SECRET = credential.getValue();
            }
            else if (credential.getName().equals("setlistAccount")) {
                SETLIST_ACCOUNT = credential.getValue();
            }
            else if (credential.getName().equals("gameKey")) {
                GAME_KEY = credential.getValue();
            }
            else if (credential.getName().equals("gameSecret")) {
                GAME_SECRET = credential.getValue();
            }
            else if (credential.getName().equals("gameAccessToken")) {
                GAME_ACCESS_TOKEN = credential.getValue();
            }
            else if (credential.getName().equals("gameAccessSecret")) {
                GAME_ACCESS_SECRET = credential.getValue();
            }
            else if (credential.getName().equals("gameAccount")) {
                GAME_ACCOUNT = credential.getValue();
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
