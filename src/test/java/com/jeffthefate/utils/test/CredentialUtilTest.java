package com.jeffthefate.utils.test;

import com.jeffthefate.utils.Backendless;
import com.jeffthefate.utils.CredentialUtil;
import com.jeffthefate.utils.json.Credential;
import com.jeffthefate.utils.json.JsonUtil;
import junit.framework.TestCase;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;

public class CredentialUtilTest extends TestCase {

    private CredentialUtil credentialUtil = CredentialUtil.instance();
    private JsonUtil jsonUtil = JsonUtil.instance();

    /**
     * The created Parse object points to the correct application in Parse.
     */
    public void testGetCredentialedBackendless() {
        Backendless backendless = credentialUtil.getCredentialedBackendless(false,
                "/Users/jfate/Google Drive/backendlessCreds.ser");
        String response = backendless.getObject("Credential", "HLQWbSXoTC");
        Credential credential = jsonUtil.getCredential(response);
        assertEquals("Credential not correct!", credential.getValue(),
                "dmbtrivia");
        /**
        backendless = credentialUtil.getCredentialedBackendless(true, "/Users/jfate/Google Drive/backendlessCreds.ser");
        response = backendless.getObject("Credential", "IrzaFfj9EQ");
        credential = jsonUtil.getCredential(response);
        assertEquals("Credential not correct!", credential.getValue(),
                "dmbtriviatest");
         */
    }

    /**
     * The created Twitter object points to the correct account.
     */
    public void testGetCredentialedTwitter() {
        /**
        Parse parse = credentialUtil.getCredentialedParse(true,
                "/Users/jfate/Google Drive/backendlessCreds.ser");
        Configuration configuration = credentialUtil.getCredentialedTwitter
                (parse, false);
        Twitter twitter = new TwitterFactory(configuration).getInstance();
        try {
            assertEquals("Twitter configuration not correct!", 1265342035l,
                    twitter.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
         */
        Backendless backendless = credentialUtil.getCredentialedBackendless(false, "/Users/jfate/Google Drive/backendlessCreds.ser");
        Configuration configuration = credentialUtil.getCredentialedTwitter(backendless, false);
        Twitter twitter = new TwitterFactory(configuration).getInstance();
        try {
            assertEquals("Twitter configuration not correct!", 611044728l, twitter.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

}
