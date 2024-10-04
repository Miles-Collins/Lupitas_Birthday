package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileReaderUtil {

    public static JSONArray readJSONArrayFile(String fileName) {
        JSONParser jsonParser = new JSONParser();
        JSONArray birthdayArr = null;

        try (FileReader reader = new FileReader(fileName)) {
            Object obj = jsonParser.parse(reader);
            birthdayArr = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return birthdayArr;
    }
}
