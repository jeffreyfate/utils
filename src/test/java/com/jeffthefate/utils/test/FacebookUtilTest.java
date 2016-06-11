package com.jeffthefate.utils.test;

import com.jeffthefate.utils.CredentialUtil;
import com.jeffthefate.utils.Facebook;
import com.jeffthefate.utils.FacebookUtil;
import com.jeffthefate.utils.Parse;
import junit.framework.TestCase;

public class FacebookUtilTest extends TestCase {

    private CredentialUtil credentialUtil;
    private FacebookUtil facebookUtil;
    private Facebook facebook;
    private Parse parse;

    public void setUp() throws Exception {
        super.setUp();
        credentialUtil = CredentialUtil.instance();
        facebookUtil = FacebookUtil.instance();
        parse = credentialUtil.getCredentialedParse(false, "parseCreds");
        facebook = credentialUtil.getCredentialedFacebook(parse);
    }

    public void testPostMessageToPage() {
        String id = facebookUtil.postMessageToPage(facebook, "TEST");
        assertNotNull("Status ID is null!", id);
        facebook.getFacebookClient().deleteObject(id);
    }

    public void testPostPhotoToPage() {
        String id = facebookUtil.postPhotoToPage(facebook, "testPhoto.png", "TEST");
        assertNotNull("Status ID is null!", id);
        facebook.getFacebookClient().deleteObject(id);
    }
}
