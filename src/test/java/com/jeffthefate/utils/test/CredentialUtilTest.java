package com.jeffthefate.utils.test;

import com.jeffthefate.utils.CredentialUtil;
import com.jeffthefate.utils.Parse;
import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.parse.Credential;
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
    public void testGetCredentialedParse() {
        Parse parse = credentialUtil.getCredentialedParse(false,
                "parseCreds");
        String response = parse.getObject("Credential", "HLQWbSXoTC");
        Credential credential = jsonUtil.getCredential(response);
        assertEquals("Credential not correct!", credential.getValue(),
                "dmbtrivia");
        parse = credentialUtil.getCredentialedParse(true, "parseCreds");
        response = parse.getObject("Credential", "IrzaFfj9EQ");
        credential = jsonUtil.getCredential(response);
        assertEquals("Credential not correct!", credential.getValue(),
                "dmbtriviatest");
    }

    /**
     * The created Twitter object points to the correct account.
     */
    public void testGetCredentialedTwitter() {
        Parse parse = credentialUtil.getCredentialedParse(true,
                "parseCreds");
        Configuration configuration = credentialUtil.getCredentialedTwitter
                (parse, false);
        Twitter twitter = new TwitterFactory(configuration).getInstance();
        try {
            assertEquals("Twitter configuration not correct!", 1265342035l,
                    twitter.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        parse = credentialUtil.getCredentialedParse(false, "parseCreds");
        configuration = credentialUtil.getCredentialedTwitter(parse, false);
        twitter = new TwitterFactory(configuration).getInstance();
        try {
            assertEquals("Twitter configuration not correct!", 611044728l,
                    twitter.getId());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

}
