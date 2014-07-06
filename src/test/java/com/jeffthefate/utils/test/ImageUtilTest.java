package com.jeffthefate.utils.test;

import com.jeffthefate.utils.ImageUtil;
import junit.framework.TestCase;

public class ImageUtilTest extends TestCase {

    private ImageUtil imageUtil = ImageUtil.instance();

    public void testCompareImages() {
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/testCompareImagesBaseline1.png",
                "src/test/resources/testCompareImagesBaseline1.png");
        assertEquals("Images are not exact same!", 0.0, percentDiff);
        percentDiff = imageUtil.compareImages(
                "src/test/resources/testCompareImagesBaseline1.png",
                "src/test/resources/testCompareImagesNew1.png");
        assertEquals("Images are not exact same!", 0.0, percentDiff);
        percentDiff = imageUtil.compareImages(
                "src/test/resources/testCompareImagesBaseline1.png",
                "src/test/resources/testCompareImagesOpposite1.png");
        assertEquals("Images are not opposite!", 100.0, percentDiff);
        percentDiff = imageUtil.compareImages(
                "src/test/resources/testCompareImagesBaseline1.png",
                "src/test/resources/testCompareImagesNoExist.png");
        assertEquals("Image not found isn't reporting!", -1.0, percentDiff);
    }
}
