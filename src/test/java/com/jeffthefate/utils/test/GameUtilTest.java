package com.jeffthefate.utils.test;

import com.jeffthefate.utils.GameUtil;
import junit.framework.TestCase;

public class GameUtilTest extends TestCase {

    private GameUtil gameUtil;

    public void setUp() throws Exception {
        super.setUp();
        gameUtil = GameUtil.instance();
    }

    public void testGenerateSongMatchList() {
        assertFalse("List is empty!", gameUtil.generateSongMatchList(false,
                "D:\\parseCreds").isEmpty());
    }
}
