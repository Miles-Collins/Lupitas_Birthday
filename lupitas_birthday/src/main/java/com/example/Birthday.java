package com.example;

import org.json.simple.JSONArray;

/**
 * Main class for the birthday program.
 */
public class Birthday {

    public static void main(final String[] args) {
        String pathToFile = "C:\\Users\\miles\\Computer-Science\\Java-Fall-24\\Lupitas_Birthday\\lupitas_birthday\\src\\main\\java\\com\\example\\lupita-birthday-example-files\\birthday.json";

        BirthdayManager birthdayManager = new BirthdayManager();
        JSONArray jsonData = FileReaderUtil.readJSONArrayFile(pathToFile);
        birthdayManager.initializeMap(jsonData);
        System.out.println("Birthday map initialized.");

        UserInputHandler userInputHandler = new UserInputHandler(birthdayManager);
        userInputHandler.getUserInput();
    }
}
