package com.jeffthefate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Map.Entry;

import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.ardverk.collection.PatriciaTrie;
import org.ardverk.collection.StringKeyAnalyzer;
import org.ardverk.collection.Trie;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {
	
	private static Trie<String, SongInfo> songMap;
	
	/**
     * Holds an image and audio clip that are associated with each other
     * @author Jeff Fate
     */
    private static class SongInfo {
        private int image;
        private int audio;
        
        private SongInfo(int image, int audio) {
            this.image = image;
            this.audio = audio;
        }
        
        private SongInfo() {
        	
        }
        
        public int getImage() {
            return image;
        }
        
        public int getAudio() {
            return audio;
        }
    }
	
	public static void findMatchingAudio(String songTitle) {
		songMap = new PatriciaTrie<String, SongInfo>(StringKeyAnalyzer.CHAR);
        
        songMap.put("belly belly nice", new SongInfo());
        songMap.put("belly full", new SongInfo());
        songMap.put("broken things", new SongInfo());
        songMap.put("drunken soldier", new SongInfo());
        songMap.put("gaucho", new SongInfo());
        songMap.put("if only", new SongInfo());
        songMap.put("mercy", new SongInfo());
        songMap.put("the riff", new SongInfo());
        songMap.put("rooftop", new SongInfo());
        songMap.put("snow outside", new SongInfo());
        songMap.put("sweet", new SongInfo());
        
        songMap.put("#35", new SongInfo());
        songMap.put("alligator pie", new SongInfo());
        songMap.put("baby blue", new SongInfo());
        songMap.put("dive in", new SongInfo());
        songMap.put("funny the way it is", new SongInfo());
        songMap.put("grux", new SongInfo());
        songMap.put("lying in the hands of god", new SongInfo());
        songMap.put("seven", new SongInfo());
        songMap.put("shake me like a monkey", new SongInfo());
        songMap.put("spaceman", new SongInfo());
        songMap.put("squirm", new SongInfo());
        songMap.put("time bomb", new SongInfo());
        songMap.put("why i am", new SongInfo());
        songMap.put("you and me", new SongInfo());
        
        songMap.put("american baby", new SongInfo());
        songMap.put("american baby intro", new SongInfo());
        songMap.put("dreamgirl", new SongInfo());
        songMap.put("dream girl", new SongInfo());
        songMap.put("everybody wake up", new SongInfo());
        songMap.put("hello again", new SongInfo());
        songMap.put("hunger for the great light", new SongInfo());
        songMap.put("louisiana bayou", new SongInfo());
        songMap.put("old dirt hill", new SongInfo());
        songMap.put("out of my hands", new SongInfo());
        songMap.put("smooth rider", new SongInfo());
        songMap.put("stand up", new SongInfo());
        songMap.put("steady as we go", new SongInfo());
        songMap.put("stolen away on 55th & 3rd", new SongInfo());
        songMap.put("you might die trying", new SongInfo());
        
        songMap.put("an' another thing", new SongInfo());
        songMap.put("baby", new SongInfo());
        songMap.put("dodo", new SongInfo());
        songMap.put("gravedigger", new SongInfo());
        songMap.put("grey blue eyes", new SongInfo());
        songMap.put("oh", new SongInfo());
        songMap.put("save me", new SongInfo());
        songMap.put("so damn lucky", new SongInfo());
        songMap.put("some devil", new SongInfo());
        songMap.put("stay or leave", new SongInfo());
        songMap.put("too high", new SongInfo());
        songMap.put("trouble", new SongInfo());
        songMap.put("up and away", new SongInfo());
        
        songMap.put("bartender", new SongInfo());
        songMap.put("big eyed fish", new SongInfo());
        songMap.put("busted stuff", new SongInfo());
        songMap.put("captain", new SongInfo());
        songMap.put("digging a ditch", new SongInfo());
        songMap.put("grace is gone", new SongInfo());
        songMap.put("grey street", new SongInfo());
        songMap.put("kit kat jam", new SongInfo());
        songMap.put("raven", new SongInfo());
        songMap.put("where are you going", new SongInfo());
        songMap.put("you never know", new SongInfo());
        
        songMap.put("angel", new SongInfo());
        songMap.put("dreams of our fathers", new SongInfo());
        songMap.put("everyday", new SongInfo());
        songMap.put("fool to think", new SongInfo());
        songMap.put("i did it", new SongInfo());
        songMap.put("if i had it all", new SongInfo());
        songMap.put("mother father", new SongInfo());
        songMap.put("sleep to dream her", new SongInfo());
        songMap.put("so right", new SongInfo());
        songMap.put("the space between", new SongInfo());
        songMap.put("what you are", new SongInfo());
        songMap.put("when the world ends", new SongInfo());
        
        songMap.put("crush", new SongInfo());
        songMap.put("don't drink the water",
                new SongInfo());
        songMap.put("dreaming tree", new SongInfo());
        songMap.put("halloween", new SongInfo());
        songMap.put("last stop", new SongInfo());
        songMap.put("pantala naga pampa",
                new SongInfo());
        songMap.put("pig", new SongInfo());
        songMap.put("rapunzel", new SongInfo());
        songMap.put("spoon", new SongInfo());
        songMap.put("stay wasting time", new SongInfo());
        songMap.put("stay", new SongInfo());
        songMap.put("the stone", new SongInfo());
        
        songMap.put("#41", new SongInfo());
        songMap.put("crash into me", new SongInfo());
        songMap.put("cry freedom", new SongInfo());
        songMap.put("drive in drive out", new SongInfo());
        songMap.put("let you down", new SongInfo());
        songMap.put("lie in our graves", new SongInfo());
        songMap.put("proudest monkey", new SongInfo());
        songMap.put("say goodbye", new SongInfo());
        songMap.put("so much to say", new SongInfo());
        songMap.put("too much", new SongInfo());
        songMap.put("tripping billies", new SongInfo());
        songMap.put("two step", new SongInfo());
        
        songMap.put("#34", new SongInfo());
        songMap.put("ants marching", new SongInfo());
        songMap.put("best of whats around",
                new SongInfo());
        songMap.put("dancing nancies", new SongInfo());
        songMap.put("jimi thing", new SongInfo());
        songMap.put("lover lay down", new SongInfo());
        songMap.put("pay for what you get",
                new SongInfo());
        songMap.put("rhyme and reason", new SongInfo());
        songMap.put("satellite", new SongInfo());
        songMap.put("typical situation",
                new SongInfo());
        songMap.put("warehouse", new SongInfo());
        songMap.put("what would you say",
                new SongInfo());
        
        songMap.put("christmas song", new SongInfo());
        songMap.put("i'll back you up", new SongInfo());
        songMap.put("minarets", new SongInfo());
        songMap.put("one sweet world", new SongInfo());
        songMap.put("recently", new SongInfo());
        songMap.put("seek up", new SongInfo());
        songMap.put("the song that jane likes", new SongInfo());
        
        songMap.put("encore", new SongInfo());
		
        songTitle = StringUtils.remove(songTitle, "*");
        songTitle = StringUtils.remove(songTitle, "+");
        songTitle = StringUtils.remove(songTitle, "~");
        songTitle = StringUtils.remove(songTitle, "�");
        songTitle = StringUtils.remove(songTitle, "(");
        songTitle = StringUtils.remove(songTitle, ")");
        songTitle = StringUtils.replace(songTitle, "’", "'");
        songTitle = StringUtils.strip(songTitle);
        songTitle = StringUtils.lowerCase(songTitle, Locale.ENGLISH);
        Entry<String, SongInfo> entry = songMap.select(songTitle);
    	if (songTitle.startsWith(entry.getKey())) {
    		System.out.println("Found " + songTitle);
    	}
    	else {
    		System.out.println("NOT FOUND " + songTitle);
    	}
    }
    
    public static void main( String[] args ) {
    	findMatchingAudio("Stay~");
    	findMatchingAudio("Stay (Wasting Time)~");
    	/*
    	String setId;
    	String set;
    	SimpleDateFormat oldDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	//oldDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    	SimpleDateFormat newDateFormat = new SimpleDateFormat("MMM d yyyy");
    	Date date;
    	String dateString;
    	String venueName;
    	String venueCity;
    	String venueId;
    	String postResponse;
    	List<String> setList;
    	JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
    	ObjectNode rootNode;
    	ObjectNode venueNode;
    	// Download all setlists
    	rootNode = jsonNodeFactory.objectNode();
    	venueNode = jsonNodeFactory.objectNode();
    	venueNode.put("$exists", false);
		rootNode.put("venue", venueNode);
    	HashMap<String, HashMap<String, String>> setlists =
    			getSetlistsFromResponse(getResponse("Setlist", 20, rootNode.toString()));
    	// For each
    	for (Entry<String, HashMap<String, String>> setlist :
    			setlists.entrySet()) {
    		// Get the set
    		setId = setlist.getValue().get("objectId");
    		set = setlist.getValue().get("set");
    		setList = Arrays.asList(StringUtils.split(set, "\n"));
			dateString = setList.get(0);
			try {
				date = oldDateFormat.parse(dateString);
				dateString = newDateFormat.format(date.getTime());
				set = set.replaceAll("\\d{2}/\\d{2}/\\d{4}", dateString);
				rootNode = jsonNodeFactory.objectNode();
	    		rootNode.put("set", set);
	    		putObject("Setlist", setId, rootNode.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if (setlist.getValue().get("venue") == null) {
    			// Pull out the venue and city (lines 2,3)
    			venueName = setList.get(2);
    			venueCity = setList.get(3);
    			// Look in the venue class for an already existing object
    			rootNode = jsonNodeFactory.objectNode();
    			rootNode.put("name", venueName);
    			rootNode.put("city", venueCity);
    			venueId = getVenueId(getResponse("Venue", 1,
    					rootNode.toString()));
    			// If not, create a venue object and put that in the setlist object
    			if (venueId == null) {
    				postResponse = postObject("Venue", rootNode.toString());
    				if (postResponse != null) {
    					venueId = getObjectId(postResponse);
    				}
    			}
    			// Put the venue object in the setlist object
    			else {
    				rootNode = jsonNodeFactory.objectNode();
    				venueNode = jsonNodeFactory.objectNode();
    				venueNode.put("__type", "Pointer");
    				venueNode.put("className", "Venue");
    				venueNode.put("objectId", venueId);
        			rootNode.put("venue", venueNode);
    				putObject("Setlist", setId, rootNode.toString());
    			}
    		}
    	}
    	*/
    }
    
    private static String getObjectId(String responseString) {
    	JsonFactory f = new JsonFactory();
        JsonParser jp;
        String objectId = null;
        try {
            jp = f.createJsonParser(responseString);
        	if (jp.nextToken() == JsonToken.START_OBJECT) {
        		while (jp.nextToken() == JsonToken.FIELD_NAME) {
        			if ("objectId".equals(jp.getCurrentName())) {
        				jp.nextToken();
        				objectId = jp.getText();
        			}
        		}
        	}
            jp.close(); // ensure resources get cleaned up timely and properly
        } catch (JsonParseException e) {
            System.out.println("Failed to parse " + responseString);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to parse " + responseString);
            e.printStackTrace();
        }
        return objectId;
    }
    
    private static String postObject(String className, String postString) {
    	HttpClient httpclient = HttpClientBuilder.create().build();
    	HttpEntity entity = null;
        HttpResponse response = null;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.parse.com/1/classes/");
        sb.append(className);
        String url = sb.toString();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("X-Parse-Application-Id",
        		"ImI8mt1EM3NhZNRqYZOyQpNSwlfsswW73mHsZV3R");
        httpPost.addHeader("X-Parse-REST-API-Key",
        		"1smRSlfAvbFg4AsDxat1yZ3xknHQbyhzZ4msAi5w");
        httpPost.setEntity(new StringEntity(postString,
        		ContentType.APPLICATION_JSON));
        try {
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 201) {
                System.out.println("POST to " + className + " failed!");
            }
            else {
            	entity = response.getEntity();
                if (entity != null) {
                     return EntityUtils.toString(entity);
                }
            }
        } catch (ClientProtocolException e1) {
            System.out.println("Failed to connect to " +
            		httpPost.getURI().toASCIIString());
            e1.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Failed to get setlist from " +
            		httpPost.getURI().toASCIIString());
            e1.printStackTrace();
        }
        return null;
    }
    
    private static boolean putObject(String className, String objectId,
    		String putString) {
    	HttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.parse.com/1/classes/");
        sb.append(className);
        sb.append("/");
        sb.append(objectId);
        String url = sb.toString();
        HttpPut httpPut = new HttpPut(url);
        httpPut.addHeader("X-Parse-Application-Id",
        		"ImI8mt1EM3NhZNRqYZOyQpNSwlfsswW73mHsZV3R");
        httpPut.addHeader("X-Parse-REST-API-Key",
        		"1smRSlfAvbFg4AsDxat1yZ3xknHQbyhzZ4msAi5w");
        httpPut.setEntity(new StringEntity(putString,
        		ContentType.APPLICATION_JSON));
        try {
            response = httpclient.execute(httpPut);
            if (response.getStatusLine().getStatusCode() != 200) {
            	System.out.println("PUT to " + className + " failed!");
                return false;
            }
            else {
            	return true;
            }
        } catch (ClientProtocolException e1) {
            System.out.println("Failed to connect to " +
            		httpPut.getURI().toASCIIString());
            e1.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Failed to get setlist from " +
            		httpPut.getURI().toASCIIString());
            e1.printStackTrace();
        }
        return false;
    }
    
    private static String getVenueId(String responseString) {
    	JsonFactory f = new JsonFactory();
        JsonParser jp;
        String fieldName;
        String objectId = null;
        try {
            jp = f.createJsonParser(responseString);
            JsonToken token;
        	if (jp.nextToken() == JsonToken.START_OBJECT) {
        		if (jp.nextToken() == JsonToken.FIELD_NAME &&
        				"results".equals(jp.getCurrentName())) {
        			if (jp.nextToken() == JsonToken.START_ARRAY) {
        				while ((token = jp.nextToken()) !=
        						JsonToken.END_ARRAY) {
        					if (token == JsonToken.FIELD_NAME) {
        						fieldName = jp.getCurrentName();
        						if ("objectId".equals(fieldName)) {
        							jp.nextToken();
        							objectId = jp.getText();
        							break;
        						}
        					}
        				}
        			}
        		}
        	}
            jp.close(); // ensure resources get cleaned up timely and properly
        } catch (JsonParseException e) {
            System.out.println("Failed to parse " + responseString);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to parse " + responseString);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Failed to parse " + responseString);
            e.printStackTrace();
        }
        return objectId;
    }
    
    private static String getResponse(String className, int limit, String where) {
    	HttpClient httpclient = HttpClientBuilder.create().build();
        HttpEntity entity = null;
        HttpResponse response = null;
        String responseString = null;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.parse.com/1/classes/");
        sb.append(className);
        sb.append("?limit=");
        sb.append(Integer.toString(limit));
        sb.append("&order=setDate");
        if (where != null) {
        	sb.append("&where=");
        	try {
				sb.append(URLEncoder.encode(where, "UTF-8").replace("+", "%20")
						.replace("-", "%2D"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
        }
        String url = sb.toString();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("X-Parse-Application-Id",
        		"ImI8mt1EM3NhZNRqYZOyQpNSwlfsswW73mHsZV3R");
        httpGet.addHeader("X-Parse-REST-API-Key",
        		"1smRSlfAvbFg4AsDxat1yZ3xknHQbyhzZ4msAi5w");
        try {
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
            	System.out.println("GET to " + className + " failed!");
                return null;
            }
            entity = response.getEntity();
            if (entity != null) {
                 return EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e1) {
            System.out.println("Failed to connect to " +
                    httpGet.getURI().toASCIIString());
            e1.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Failed to get setlist from " +
                    httpGet.getURI().toASCIIString());
            e1.printStackTrace();
        }
        return null;
    }
    
    private static HashMap<String, HashMap<String, String>>
    		getSetlistsFromResponse(String responseString) {
        JsonFactory f = new JsonFactory();
        JsonParser jp;
        String fieldName;
        String objectId = null;
        String set = null;
        String setDate = null;
        String venue = null;
        HashMap<String, HashMap<String, String>> setlistMap =
        		new HashMap<String, HashMap<String, String>>(0);
        HashMap<String, String> tempMap = new HashMap<String, String>(0);
        try {
            jp = f.createJsonParser(responseString);
            JsonToken token;
            boolean inObject = false;
        	if (jp.nextToken() == JsonToken.START_OBJECT) {
        		if (jp.nextToken() == JsonToken.FIELD_NAME &&
        				"results".equals(jp.getCurrentName())) {
        			if (jp.nextToken() == JsonToken.START_ARRAY) {
        				while ((token = jp.nextToken()) !=
        						JsonToken.END_ARRAY) {
        					if (token == JsonToken.START_OBJECT && !inObject) {
        						tempMap = new HashMap<String, String>(0);
        						inObject = true;
        						venue = null;
        					}
        					else if (token == JsonToken.FIELD_NAME) {
        						fieldName = jp.getCurrentName();
        						if ("set".equals(fieldName)) {
        							jp.nextToken();
        							set = jp.getText();
        						}
        						else if ("setDate".equals(fieldName)) {
        							while ((token = jp.nextToken()) !=
        									JsonToken.END_OBJECT) {
        								if (token == JsonToken.FIELD_NAME &&
        										jp.getCurrentName().equals("iso")) {
        									jp.nextToken();
        									setDate = jp.getText(); // Date string
        								}
        							}
        						}
        						else if ("plays".equals(fieldName)) {
        							while ((token = jp.nextToken()) !=
        									JsonToken.END_OBJECT) {
        								// TODO Something with plays?
        							}
        						}
        						else if ("venue".equals(fieldName)) {
        							while ((token = jp.nextToken()) !=
        									JsonToken.END_OBJECT) {
        								if (token == JsonToken.FIELD_NAME &&
        										jp.getCurrentName().equals("objectId")) {
        									jp.nextToken();
        									venue = jp.getText();
        								}
        							}
        						}
        						else if ("objectId".equals(fieldName)) {
        							jp.nextToken();
        							objectId = jp.getText();
        						}
        					}
        					else if (token == JsonToken.END_OBJECT) {
        						tempMap.put("set", set);
        						tempMap.put("setDate", setDate);
        						tempMap.put("objectId", objectId);
        						tempMap.put("venue", venue);
        						setlistMap.put(setDate, tempMap);
        						inObject = false;
        					}
        				}
        			}
        		}
        	}
            jp.close(); // ensure resources get cleaned up timely and properly
        } catch (JsonParseException e) {
            System.out.println("Failed to parse " + responseString);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to parse " + responseString);
            e.printStackTrace();
        }
        System.out.println(setlistMap);
        return setlistMap;
    }
    /*
    private String postSetlist(String json) {
    	HttpClient httpclient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        String objectId = null;
        HttpPost httpPost = new HttpPost(
        		"https://api.parse.com/1/classes/Setlist");
        httpPost.addHeader("X-Parse-Application-Id",
        		"ImI8mt1EM3NhZNRqYZOyQpNSwlfsswW73mHsZV3R");
        httpPost.addHeader("X-Parse-REST-API-Key",
        		"1smRSlfAvbFg4AsDxat1yZ3xknHQbyhzZ4msAi5w");
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        try {
            StringEntity reqEntity = new StringEntity(json, "UTF-8");
            httpPost.setEntity(reqEntity);
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 201) {
                System.out.println("POST of setlist failed!");
                System.out.println(json);
            } 
            else {
            	HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                 String responseString = EntityUtils.toString(
	                		 response.getEntity());
	                 objectId = getSimpleObjectIdFromResponse(responseString);
	            }
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed to create entity from " + json);
            e.printStackTrace();
        } catch (ClientProtocolException e1) {
            System.out.println("Failed to connect to " +
                    httpPost.getURI().toASCIIString());
            e1.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Failed to get setlist from " +
                    httpPost.getURI().toASCIIString());
            e1.printStackTrace();
        }
        return objectId;
    }
    
    private String getSetlistJsonString(String latestSetlist) {
        currDateString = getNewSetlistDateString(latestSetlist);
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode rootNode = factory.objectNode();
        ObjectNode dateNode = factory.objectNode();
        dateNode.put("__type", "Date");
        dateNode.put("iso", currDateString);
        rootNode.put("set", latestSetlist);
        rootNode.put("setDate", dateNode);
        return rootNode.toString();
    }
    */
}
