package com.example;

import java.util.Scanner;

public class UserInputHandler {

    private BirthdayManager birthdayManager;

    public UserInputHandler(BirthdayManager birthdayManager) {
        this.birthdayManager = birthdayManager;
    }

    /**
     * Gets user input and prints the birthday of the person with the given
     * name.
     */
    public void getUserInput() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a name: ");
            String name = input.nextLine();
            birthdayManager.printBirthday(name, input);
        }
    }
}
