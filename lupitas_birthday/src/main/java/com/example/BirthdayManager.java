package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BirthdayManager {

    private HashMap<String, String> birthdayMap = new HashMap<>();

    public void initializeMap(JSONArray jsonData) {
        String birthday, name;
        JSONObject obj;
        for (Integer i = 0; i < jsonData.size(); i++) {
            obj = (JSONObject) jsonData.get(i);
            birthday = (String) obj.get("birthday");
            name = (String) obj.get("name");

            birthdayMap.put(name, birthday);
        }
    }

    public ArrayList<String> getPeople(String name) {
        ArrayList<String> foundPeople = new ArrayList<>();
        for (String user : birthdayMap.keySet()) {
            if (user.contains(name)) {
                foundPeople.add(user);
            }
        }
        return foundPeople;
    }

    public String getBirthday(String name) {
        for (String user : birthdayMap.keySet()) {
            if (user.contains(name)) {
                return birthdayMap.get(user);
            }
        }
        return birthdayMap.get(name);
    }

    public void printBirthday(String name) {
        ArrayList<String> foundPeople = getPeople(name);
        if (foundPeople.size() == 1) {
            System.out.println(name + "'s birthday is " + getBirthday(name));
        } else if (!foundPeople.isEmpty()) {
            System.out.println("Here is a list of names that match your input. Please try to narrow down your search by using first AND last name.");
            System.out.println(foundPeople);
        } else {
            System.out.println(name + " not found in the birthday list. Please try again.");
            try (Scanner input = new Scanner(System.in)) {
                String newName = UserInputHandler.getUserInput(input);
                printBirthday(newName);
                getBirthday(newName);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }
}
