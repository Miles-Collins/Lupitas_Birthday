package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Manages the birthday data and searches for people based on their names.
 */
public class BirthdayManager {

    private HashMap<String, String> birthdayMap = new HashMap<>();

    /**
     * Initializes the birthday map with the data from the JSON file.
     *
     * @param jsonData The JSON data to initialize the map with.
     */
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

    /**
     * Searches for people whose names contain the given name.
     *
     * @param name The name to search for.
     * @return A list of matching names.
     */
    public ArrayList<String> getPeople(String name) {
        ArrayList<String> foundPeople = new ArrayList<>();
        for (String user : birthdayMap.keySet()) {
            if (user.toLowerCase().contains(name.toLowerCase())) {
                foundPeople.add(user);
            }
        }
        return foundPeople;
    }

    /**
     * Searches for people whose names contain the given name.
     *
     * @param name The name to search for.
     * @return A list of matching names.
     */
    public String getBirthday(String name) {
        for (String user : birthdayMap.keySet()) {
            if (user.toLowerCase().contains(name.toLowerCase())) {
                return birthdayMap.get(user);
            }
        }
        return null;
    }

    /**
     * Prompts the user to search for another name.
     *
     * @param input
     */
    public void promptUser(Scanner input) {
        System.out.print("Would you like to search for another name? (yes/no): ");
        String response = input.next().trim();
        switch (response.toLowerCase()) {
            case "yes" -> {
                System.out.print("Please enter the name of the person you would like to search for: ");
                String name = input.nextLine().trim();
                if (name.isEmpty()) {
                    name = input.nextLine().trim();
                }
                printBirthday(name, input);
            }
            case "no" -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                promptUser(input);
            }
        }
    }

    private void displayFoundPeople(ArrayList<String> foundPeople) {
        System.out.println("Here is a list of names that match your input. Please type in the number associated with the name, or try the full name of the person you're looking for.");
        for (int i = 1; i <= foundPeople.size(); i++) {
            if (i != foundPeople.size()) {
                System.out.print(i + ": " + foundPeople.get(i - 1) + ", ");
            } else {
                System.out.print(i + ": " + foundPeople.get(i - 1) + ".\n");
            }
        }
    }

    private void handleUserSelection(ArrayList<String> foundPeople, Scanner input) {
        System.out.print("Please enter the number associated with the name or enter another name: ");
        if (input.hasNextInt()) {
            int index = input.nextInt();
            input.nextLine(); // Consume the newline character
            printBirthday(foundPeople.get(index - 1), input);
        } else {
            String newName = input.nextLine().trim();
            printBirthday(newName, input);
        }
    }

    public void printBirthday(String name, Scanner input) {
        ArrayList<String> foundPeople = getPeople(name);
        if (foundPeople.size() == 1) {
            System.out.println(foundPeople.get(0) + "'s birthday is " + getBirthday(foundPeople.get(0)));
            promptUser(input);
        } else if (!foundPeople.isEmpty()) {
            displayFoundPeople(foundPeople);
            handleUserSelection(foundPeople, input);
        } else {
            System.out.println(name + " not found in the birthday list. Please try again.");
            promptUser(input);
        }
    }

}
