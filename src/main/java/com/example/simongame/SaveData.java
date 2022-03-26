package com.example.simongame;

import org.json.JSONTokener;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.json.JSONObject;
import org.json.JSONArray;

public class SaveData extends Thread {
    private JSONObject results;
    private JSONArray array;
    private final int totalScore;
    private final String name;
    private PrintWriter printWriter;
    public SaveData(int totalScore, String name) {
        this.totalScore = totalScore;
        this.name = name;
    }
    private void createJSONObject() {
        File file = new File(MainMenu.path);
        try {
            file.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        results = new JSONObject();
        array = new JSONArray();
        results.put(MainMenu.KEY_RESULTS, array);
    }
    private void startRecord() {
        try {
            InputStream inputStream = new FileInputStream(MainMenu.path);
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            results = new JSONObject(jsonTokener);
            array = results.getJSONArray(MainMenu.KEY_RESULTS);
        } catch(IOException e) {
            e.printStackTrace();
            createJSONObject();
        }
        JSONObject currentResult = new JSONObject();
        currentResult.put(MainMenu.KEY_NAME, name);
        currentResult.put(MainMenu.KEY_SCORE, totalScore);
        array.put(currentResult);
        if(array.length() >= 10) {

        }
        results.put(MainMenu.KEY_RESULTS, array);
        try {
            printWriter = new PrintWriter(MainMenu.path, StandardCharsets.UTF_8);
            printWriter.write(results.toString());
            printWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        super.run();
        startRecord();
    }
}