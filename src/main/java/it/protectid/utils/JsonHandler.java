package it.protectid.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonHandler {


    public String read() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("models.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray modelList = (JSONArray) obj;
            System.out.println(modelList);


            return ((JSONArray) obj).toJSONString();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
