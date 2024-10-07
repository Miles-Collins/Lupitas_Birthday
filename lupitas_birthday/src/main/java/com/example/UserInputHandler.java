package com.example;

import java.util.Scanner;

public class UserInputHandler {

    private BirthdayManager birthdayManager;

    public UserInputHandler(BirthdayManager birthdayManager) {
        this.birthdayManager = birthdayManager;
    }

    public void getUserInput() {
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a name: ");
                String name = input.next();
                birthdayManager.printBirthday(name, input);
            }
        }
    }
}
