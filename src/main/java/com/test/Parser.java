//Oleg Gema
package com.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Parser {
    public static String parse(String s)  {
        String result = null;

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JSONParser parser = new JSONParser();
            JSONObject jo = (JSONObject) parser.parse(s);
            result = gson.toJson(jo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
