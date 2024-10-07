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
        // System.out.println("Birthday map initialized with data: " + birthdayMap);
    }

    public ArrayList<String> getPeople(String name) {
        // System.out.println("Searching for " + name + " in the birthday map.");
        // System.out.println("Birthday map: " + birthdayMap);
        ArrayList<String> foundPeople = new ArrayList<>();
        for (String user : birthdayMap.keySet()) {
            if (user.toLowerCase().contains(name.toLowerCase())) {
                foundPeople.add(user);
            }
        }
        return foundPeople;
    }

    public String getBirthday(String name) {
        // System.out.println("Searching for " + name + " in the birthday map.");
        // System.out.println("Birthday map: " + birthdayMap);
        for (String user : birthdayMap.keySet()) {
            if (user.toLowerCase().contains(name.toLowerCase())) {
                return birthdayMap.get(user);
            }
        }
        return null;
    }

    public void promptUser(Scanner input) {
        System.out.print("Would you like to search for another name? (yes/no): ");
        String response = input.next();
        switch (response.toLowerCase()) {
            case "yes" -> {
                System.out.print("Please enter the name of the person you would like to search for: ");
                String name = input.next();
                printBirthday(name, input);
            }
            case "no" -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid input. Please try again.");
                promptUser(input);
            }
        }
    }

    public void printBirthday(String name, Scanner input) {
        ArrayList<String> foundPeople = getPeople(name);
        if (foundPeople.size() == 1) {
            System.out.println(name + "'s birthday is " + getBirthday(name));
            promptUser(input);
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
            if (input.hasNextInt()) {
                int index = input.nextInt();
                printBirthday(foundPeople.get(index - 1), input);
            } else {
                String newName = input.next();
                printBirthday(newName, input);
            }
        } else {
            System.out.println(name + " not found in the birthday list. Please try again.");
            promptUser(input);
        }
    }
}
