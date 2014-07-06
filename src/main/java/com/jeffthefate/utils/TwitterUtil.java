package com.jeffthefate.utils;

import org.apache.log4j.Logger;
import twitter4j.*;
import twitter4j.conf.Configuration;

import java.io.File;

public class TwitterUtil {

    private static TwitterUtil twitterUtil;

    private Logger logger = Logger.getLogger(TwitterUtil.class);

    public static TwitterUtil instance() {
        if (twitterUtil == null) {
            twitterUtil = new TwitterUtil();
        }
        return twitterUtil;
    }

    public Status updateStatus(Configuration twitterConfig, String message,
            File file, long replyTo) {
        Twitter twitter = new TwitterFactory(twitterConfig).getInstance();
        StatusUpdate statusUpdate = new StatusUpdate(message);
        if (file != null) {
            statusUpdate.media(file);
        }
        statusUpdate.setInReplyToStatusId(replyTo);
        Status status = null;
        try {
            status = twitter.updateStatus(statusUpdate);
        } catch (TwitterException e) {
            logger.info("Failed to get timeline: " + e.getMessage());
            e.printStackTrace();
            sendDirectMessage(twitterConfig, "jeffthefate",
                    "Error updating status: " + e.getMessage());
        }
        return status;
    }

    public void sendDirectMessage(Configuration tweetConfig, String screenName,
            String message) {
        Twitter twitter = new TwitterFactory(tweetConfig).getInstance();
        try {
            twitter.sendDirectMessage(screenName, message);
        } catch (TwitterException e) {
            logger.info("Unable to send direct message!");
            e.printStackTrace();
        }
    }
}
