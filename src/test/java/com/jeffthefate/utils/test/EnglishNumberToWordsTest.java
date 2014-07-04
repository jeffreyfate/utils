package com.jeffthefate.utils.test;

import com.jeffthefate.utils.EnglishNumberToWords;
import junit.framework.TestCase;

public class EnglishNumberToWordsTest extends TestCase {

    public void testConvert() {
        assertEquals("Number conversion not equal!", "nine hundred eighty " +
                "seven million six hundred fifty four thousand three hundred " +
                "twenty one", EnglishNumberToWords.convert(987654321));
        assertNull("Negative number didn't return null!",
                EnglishNumberToWords.convert(-1));
        assertEquals("0 didn't return zero!", "zero",
                EnglishNumberToWords.convert(0));
    }

}
