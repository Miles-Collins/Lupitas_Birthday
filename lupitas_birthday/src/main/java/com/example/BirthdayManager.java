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
            System.out.println("Here is a list of names that match your input. Please type in the number associated with the name, or try the full name of the person you're looking for.");
            for (int i = 1; i <= foundPeople.size(); i++) {
                if (i != foundPeople.size()) {
                    System.out.print(i + ": " + foundPeople.get(i - 1) + ", ");
                } else {
                    System.out.print(i + ": " + foundPeople.get(i - 1) + ".\n");
                }
            }
            System.out.print("Please enter the number associated with the name or enter another name: ");
            try (Scanner input = new Scanner(System.in)) {
                if (input.hasNextInt()) {
                    int index = input.nextInt();
                    printBirthday(foundPeople.get(index - 1));
                } else {
                    String newName = input.next();
                    printBirthday(newName);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } else {
            System.out.println(name + " not found in the birthday list. Please try again.");
        }
    }
}
