package com.jeffthefate.utils;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    private static ImageUtil imageUtil;

    private Logger logger = Logger.getLogger(ImageUtil.class);

    public static ImageUtil instance() {
        if (imageUtil == null) {
            imageUtil = new ImageUtil();
        }
        return imageUtil;
    }

    public double compareImages(String imagePathBaseline,
            String imagePathNew) {
        BufferedImage img1;
        BufferedImage img2;
        try {
            img1 = ImageIO.read(new File(imagePathBaseline));
            img2 = ImageIO.read(new File(imagePathNew));
        } catch (IOException e) {
            logger.error("Unable to read image file!");
            return -1.0;
        }
        int width1 = img1.getWidth(null);
        int width2 = img2.getWidth(null);
        int height1 = img1.getHeight(null);
        int height2 = img2.getHeight(null);
        if ((width1 != width2) || (height1 != height2)) {
            logger.error("Image dimensions are different!");
            return -1.0;
        }
        long diff = 0;
        int rgb1;
        int rgb2;
        int r1;
        int r2;
        int g1;
        int g2;
        int b1;
        int b2;
        for (int i = 0; i < height1; i++) {
            for (int j = 0; j < width1; j++) {
                rgb1 = img1.getRGB(j, i);
                rgb2 = img2.getRGB(j, i);
                r1 = (rgb1 >> 16) & 0xff;
                g1 = (rgb1 >>  8) & 0xff;
                b1 = (rgb1      ) & 0xff;
                r2 = (rgb2 >> 16) & 0xff;
                g2 = (rgb2 >>  8) & 0xff;
                b2 = (rgb2      ) & 0xff;
                diff += Math.abs(r1 - r2);
                diff += Math.abs(g1 - g2);
                diff += Math.abs(b1 - b2);
            }
        }
        double n = width1 * height1 * 3;
        double p = diff / n / 255.0;
        return p * 100.0;
    }
}
