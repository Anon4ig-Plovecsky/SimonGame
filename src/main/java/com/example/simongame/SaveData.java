package com.example.simongame;

import java.nio.charset.StandardCharsets;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.*;
import java.io.*;

public class SaveData extends Thread {
    private JSONObject results;
    private JSONArray array;
    private final int totalScore;
    private final String name;
    public SaveData(int totalScore, String name) {
        this.totalScore = totalScore;
        this.name = name;
    }
    private void createJSONObject() {
        File file = new File(MainMenu.path);
        try {
            if(file.createNewFile())
                throw new IOException("Can't create file");
        } catch(IOException e) {
            e.printStackTrace();
        }
        results = new JSONObject();
        array = new JSONArray();
        results.put(MainMenu.KEY_RESULTS, array);
    }
    public void startRecord() {
        try {
            InputStream inputStream = new FileInputStream(MainMenu.path);
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            results = new JSONObject(jsonTokener);
            array = results.getJSONArray(MainMenu.KEY_RESULTS);
            inputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
            createJSONObject();
        }
        JSONObject currentResult = new JSONObject();
        currentResult.put(MainMenu.KEY_NAME, name);
        currentResult.put(MainMenu.KEY_SCORE, totalScore);
        array.put(currentResult);
        List<JSONObject> listArray = new ArrayList<>();
        for(int i = 0; i < array.length(); i++)
            listArray.add(array.getJSONObject(i));
        listArray.sort(new Sorting());
        array = new JSONArray();
        for (JSONObject jsonObject : listArray)
            array.put(jsonObject);
        results.put(MainMenu.KEY_RESULTS, array);
        if(array.length() >= 10)
            array.remove(10);
        try {
            PrintWriter printWriter = new PrintWriter(MainMenu.path, StandardCharsets.UTF_8);
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
class Sorting implements Comparator<JSONObject> {
    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        return o2.getInt(MainMenu.KEY_SCORE) - o1.getInt(MainMenu.KEY_SCORE);
    }
}