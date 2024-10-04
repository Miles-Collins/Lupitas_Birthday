package com.example;

import java.util.*;
import org.json.simple.*;
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

            System.out.println("name = " + name);
            System.out.println("birthday = " + birthday);
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

    public void getBirthday(String name) {
        ArrayList<String> foundPeople = getPeople(name);
        if (foundPeople.size() == 1) {
            System.out.println(name + "'s birthday is " + birthdayMap.get(name));
        } else if (!foundPeople.isEmpty()) {
            System.err.println("Here is a list of names that match your input. Please try to narrow down your search by using first AND last name.");
            System.out.println(foundPeople);
        } else {
            System.out.println(name + " not found in the birthday list. Please try again.");
            Scanner newInput = new Scanner(System.in);
            String newName = UserInputHandler.getUserInput(newInput);
            getBirthday(newName);
        }
    }
}
