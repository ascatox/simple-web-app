package it.protectid.utils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;

public class JsonHandler {


    public enum TypeOfModel {
        ChassisDTO, ProcessStep, ProcessStepResultDTO
    }

    public  String convertToJson(Object obj) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE); //This property put data in upper camel case
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public  Object convertFromJson(String json, Class clazz) throws Exception {
        try {
            if (StringUtils.isEmpty(json))
                throw new Exception("Json data is EMPTY");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true); //This property serialize/deserialize not considering the case of fields
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

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
