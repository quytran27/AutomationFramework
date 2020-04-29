package com.automation.framework.core.datadriven.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileHelper {

    public static FileReader loadFile(String filePath) throws FileNotFoundException {
        try {
            FileReader reader = new FileReader(filePath);
            return reader;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static ArrayList<HashMap<String, String>> getDataFromJsonFile(String filePath) throws IOException {
        ArrayList<HashMap<String, String>> List = new ArrayList<HashMap<String, String>>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = JsonFileHelper.loadFile(filePath);
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArr = (JSONArray) obj;
            //Iterate over item of array
            jsonArr.forEach(item -> {
               //List.add(JsonFileHelper.parseEmployeeObject((JSONObject) item));
               List.add(JsonFileHelper.parseUIScenario((JSONObject) item));
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return List;
    }

    private static HashMap<String, String> parseEmployeeObject(JSONObject item) {
        //Get employee object within list
        HashMap<String, String> data = new HashMap<>();
        JSONObject obj = (JSONObject) item.get("postInfo");

        if (obj != null) {
            //Get user ID
            data.put("userId", obj.get("userId").toString());

            //Get id
            data.put("id", obj.get("id").toString());

            //Get title
            data.put("title", obj.get("title").toString());

            //Get body
            data.put("body", obj.get("body").toString());
        }

        return data;
    }

    private static HashMap<String, String> parseUIScenario(JSONObject item) {
        //Get Scenario object
        HashMap<String, String> data = new HashMap<>();
        JSONObject obj = (JSONObject) item.get("scenario");

        if (obj != null) {
            data.put("lastName", obj.get("lastName").toString());
            data.put("firstName", obj.get("firstName").toString());
            data.put("expectedErrorMessage", obj.get("expectedErrorMessage").toString());
        }

        return data;
    }
}


