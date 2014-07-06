package com.jeffthefate.utils.test;

import com.jeffthefate.utils.json.JsonUtil;
import com.jeffthefate.utils.json.QuestionResults;
import com.jeffthefate.utils.json.SetlistResults;
import com.jeffthefate.utils.json.SongResults;
import junit.framework.TestCase;

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

    public void testGetQuestionResultsNullScore() {
        JsonUtil jsonUtil = JsonUtil.instance();
        String resultsString = "{\"results\":[{\"answer\":\"Away From The " +
                "World\",\"category\":\"Albums\",\"question\":\"What album " +
                "was released on September 11th, 2012?\",\"score\":null," +
                "\"trivia\":1,\"createdAt\":\"2012-11-28T22:09:22.040Z\"," +
                "\"updatedAt\":\"2014-05-21T00:32:57.987Z\"," +
                "\"objectId\":\"0A8EFRHoqk\"}]}";
        QuestionResults questionResults = jsonUtil.getQuestionResults(
                resultsString);
        System.out.println(questionResults.getResults());
    }

}
