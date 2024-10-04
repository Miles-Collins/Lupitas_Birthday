package com.example;

import java.util.Scanner;

public class UserInputHandler {

    public static String getUserInput(Scanner input) {
        System.out.print("Enter a name: ");
        return input.nextLine();
    }
}
