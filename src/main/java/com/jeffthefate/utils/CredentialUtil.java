package com.jeffthefate.utils;

import java.io.File;
import java.util.HashMap;

public class CredentialUtil {

    private final String CREDENTIAL_FILE = System.getProperty("user.dir") +
            File.separator + "parseCreds";

    private static CredentialUtil credetialUtil;
    private static FileUtil fileUtil;

    public static CredentialUtil instance() {
        if (credetialUtil == null) {
            credetialUtil = new CredentialUtil();
        }
        if (fileUtil == null) {
            fileUtil = FileUtil.instance();
        }
        return credetialUtil;
    }

    public Parse getCredentialedParse(boolean isDev) {
        HashMap<String, String> credentialsMap = fileUtil.readHashMapFromFile(
                CREDENTIAL_FILE);
        return new Parse(isDev ? credentialsMap.get("devAppId") :
                credentialsMap.get("prodAppId"), isDev ?
                credentialsMap.get("devRestKey") :
                credentialsMap.get("prodAppId"));
    }
}
