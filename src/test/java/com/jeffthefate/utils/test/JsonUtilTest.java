package com.jeffthefate.utils.test;

import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.SetlistResults;
import com.jeffthefate.utils.json.SongResults;
import junit.framework.TestCase;

/**
 * Created by Jeff on 6/19/2014.
 */
public class JsonUtilTest extends TestCase {

    public void testGetSetlistResults() {
        JsonUtil jsonUtil = JsonUtil.instance();
        String resultsString =
                "{\"results\":[{\"venue\":{\"__type\":\"Pointer\"," +
                "\"className\":\"Venue\",\"objectId\":\"sRnwmhzwPP\"},\"set\":\"Jun 14 2014\\nDave Matthews Band\\nSusquehanna Bank Center\\nCamden, NJ\\n\\nBartender*\\nSlip Slidin Away+\\nPay For What You Get~\\nTake Me To Tomorrow~\\nSo Damn Lucky ->\\n(Thank You (Falettinme Be Mice Elf Agin))\\nStolen Away On 55th & 3rd\\nMinarets\\nLie In Our Graves\\n\\nSet Break\\n\\nNotes:\\n* Dave And Tim\\n+ Carter, Dave, Rashawn and Tim\\n~ Carter, Dave, Stefan and Tim\\n(song name) indicates a partial song\\n-> indicates a segue into next song\",\"setDate\":{\"__type\":\"Date\",\"iso\":\"2014-06-14T00:00:00.000Z\"},\"plays\":{\"__type\":\"Relation\",\"className\":\"Play\"},\"createdAt\":\"2014-06-14T22:30:31.951Z\",\"updatedAt\":\"2014-06-15T00:52:19.661Z\",\"objectId\":\"UCnjunxzHy\"}]}";
        SetlistResults setlistResults = jsonUtil.getSetlistResults(resultsString);
        System.out.println(setlistResults.getResults());
    }

    public void testGetSongResultsEmpty() {
        JsonUtil jsonUtil = JsonUtil.instance();
        String resultsString = "{\"results\":[]}";
        SongResults songResults = jsonUtil.getSongResults(resultsString);
        System.out.println(songResults.getResults());
    }

}
