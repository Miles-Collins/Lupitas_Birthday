package com.example;

import java.util.Scanner;

import org.json.simple.JSONArray;

public class Birthday {

    public static void main(final String[] args) {
        String pathToFile = "C:\\Users\\miles\\Computer-Science\\Java-Fall-24\\Lupitas_Birthday\\lupitas_birthday\\src\\main\\java\\com\\example\\lupita-birthday-example-files\\birthday.json";

        BirthdayManager birthdayManager = new BirthdayManager();
        JSONArray jsonData = FileReaderUtil.readJSONArrayFile(pathToFile);
        birthdayManager.initializeMap(jsonData);

        System.out.println("Reading user input into a string");

        try (Scanner input = new Scanner(System.in)) {
            String name = UserInputHandler.getUserInput(input);
            birthdayManager.printBirthday(name);
        }
    }
}
