package com.jeffthefate.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeff on 6/18/2014.
 */
public class FileUtil {

    private static FileUtil fileUtil;

    private Logger logger = Logger.getLogger(FileUtil.class);

    public static FileUtil instance() {
        if (fileUtil == null) {
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }

    public boolean saveToFile(String filename, List<String> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<String> readFromFile(String filename) {
        List<String> banList;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    fileInputStream);
            banList = (List<String>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            return new ArrayList<String>(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return banList;
    }

    public void writeStringToFile(String output, String filename) {
        if (output != null) {
            writeBufferToFile(output.getBytes(), filename);
        }
    }

    public String readStringFromFile(String filename) {
        return bytesToString(readBufferFromFile(filename)).toString();
    }

    private void writeBufferToFile(byte[] buffer, String filename) {
        BufferedOutputStream bufStream;
        try {
            bufStream = new BufferedOutputStream(
                    new FileOutputStream(filename, false), buffer.length);
            bufStream.write(buffer);
            bufStream.flush();
            bufStream.close();
        } catch (FileNotFoundException e) {
            logger.info("File not found: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("BufferedOutputStream failed for: " + filename);
            e.printStackTrace();
        }
    }

    private byte[] readBufferFromFile(String filename) {
        File file = new File(filename);
        byte[] buffer = new byte[(int)file.length()];
        BufferedInputStream bufStream;
        try {
            bufStream = new BufferedInputStream(new FileInputStream(file),
                    buffer.length);
            bufStream.read(buffer);
            bufStream.close();
        } catch (FileNotFoundException e) {
            logger.info(filename + " not found!");
        } catch (IOException e) {
            logger.info(filename + " IO Exception!");
        } catch (IllegalArgumentException e) {
            logger.info("File stream is <= 0");
            return new byte[0];
        }
        return buffer;
    }

    public void appendListToFile(List<String> output, String filename) {
        String old = readStringFromFile(filename);
        StringBuilder sb = new StringBuilder();
        sb.append(old);
        for (String line : output) {
            sb.append(line);
            sb.append("\n");
        }
        writeStringToFile(sb.toString(), filename);
    }

    public void appendStringToFile(String output, String filename) {
        String old = readStringFromFile(filename);
        StringBuilder sb = new StringBuilder();
        sb.append(old);
        sb.append(output);
        sb.append("\n");
        writeStringToFile(sb.toString(), filename);
    }

    public List<String> getListFromFile(String filename) {
        String content = readStringFromFile(filename);
        String[] questionIds = content.split("\n");
        if (questionIds.length < 1)
            return null;
        List<String> questionList = new ArrayList<String>(0);
        questionList.clear();
        for (int i = 0; i < questionIds.length; i++) {
            if (!StringUtils.isEmpty(questionIds[i]))
                questionList.add(questionIds[i]);
        }
        return questionList;
    }

    public Map<String, Integer> getMapFromFile(String filename) {
        String content = readStringFromFile(filename);
        String[] scores = content.split("\n");
        String[] user;
        Map<String, Integer> scoreMap = new HashMap<String, Integer>();
        for (int i = 0; i < scores.length; i++) {
            if (!StringUtils.isEmpty(scores[i])) {
                user = scores[i].split(" | ");
                try {
                    scoreMap.put(user[0], Integer.parseInt(user[1]));
                } catch (NumberFormatException e) {}
            }
        }
        return scoreMap;
    }

    private CharBuffer bytesToString(byte[] input) {
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer srcBuffer = ByteBuffer.wrap(input);
        CharBuffer charBuffer = null;
        try {
            charBuffer = decoder.decode(srcBuffer);
        } catch (CharacterCodingException e) {}
        return charBuffer;
    }

}
