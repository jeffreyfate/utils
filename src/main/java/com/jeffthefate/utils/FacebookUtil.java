package com.jeffthefate.utils;

import com.restfb.BinaryAttachment;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class FacebookUtil {

    private static FacebookUtil facebookUtil;

    private Logger logger = Logger.getLogger(FacebookUtil.class);

    public static FacebookUtil instance() {
        if (facebookUtil == null) {
            facebookUtil = new FacebookUtil();
        }
        return facebookUtil;
    }

    public String postMessageToPage(Facebook facebook, String message) {
        FacebookType publishMessageResponse = facebook.getFacebookClient().publish(
                "/" + facebook.getFacebookPageId() + "/feed", FacebookType.class, Parameter.with("message", message));
        return publishMessageResponse.getId();
    }

    public String postPhotoToPage(Facebook facebook, String filename, String message) {
        try {
            FacebookType publishPhotoMessage = facebook.getFacebookClient().publish("me/photos", FacebookType.class,
                    BinaryAttachment.with(filename, FileUtils.readFileToByteArray(new File(filename))),
                    Parameter.with("message", message));
            return publishPhotoMessage.getId();
        } catch (IOException e) {
            logger.error("Unable to read file " + filename);
            return null;
        }
    }

}
