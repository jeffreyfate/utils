package com.jeffthefate.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileUtil {

    private static FileUtil fileUtil;

    private Logger logger = Logger.getLogger(FileUtil.class);

    public static FileUtil instance() {
        if (fileUtil == null) {
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }

    public boolean saveHashMapToFile(String filename,
            HashMap<Object, Object> map) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    fileOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public HashMap<Object, Object> readHashMapFromFile(String filename) {
        HashMap<Object, Object> map;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    fileInputStream);
            map = (HashMap<Object, Object>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            return new HashMap<>(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public boolean saveListToFile(String filename, List<Object> list) {
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

    public List<Object> readListFromFile(String filename) {
        List<Object> banList;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    fileInputStream);
            banList = (List<Object>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(0);
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
        logger.info("file length: " + file.length());
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

    public void appendListToFile(List<Object> output, String filename) {
        List<Object> old = readListFromFile(filename);
        old.addAll(output);
        saveListToFile(filename, old);
    }

    public void appendStringToFile(String output, String filename) {
        String old = readStringFromFile(filename);
        writeStringToFile(old + output + "\n", filename);
    }

    private CharBuffer bytesToString(byte[] input) {
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer srcBuffer = ByteBuffer.wrap(input);
        CharBuffer charBuffer = null;
        try {
            charBuffer = decoder.decode(srcBuffer);
        } catch (CharacterCodingException e) {
            logger.error("Can't encode bytes to a string!", e);
        }
        return charBuffer;
    }

    public ArrayList<String> getListOfFiles(String parentDir,
            final String filter) {
        File dir = new File(parentDir);
        String[] files = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(filter);
            }
        });
        return new ArrayList<>(Arrays.asList(files));
    }

}
