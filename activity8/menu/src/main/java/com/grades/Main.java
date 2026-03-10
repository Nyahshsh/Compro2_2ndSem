package com.grades;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Universal Scanner
        private static Scanner sc = new Scanner(System.in);

        // Arrays
        int MAX = 100;
        subjects

        // User Welcome
        System.out.println("===== WELCOME TO THE GRADES SYSTEM =====\n");
        mainMenu();

    }

    public static void mainMenu() {
        int number = 0;

        do {
            System.out.println("===== MAIN MENU =====");
            System.out.println("""
                    [1] Enter Grades
                    [2] Display Grades
                    [3] Exit
                        """);

            System.out.println("\nEnter Number: ");
            number = sc.nextInt();

            switch (number) {
                case 1:
                    enterGradesMenu();
                case 2:
                    displayGradesMenu();
                case 3:
                    System.out.println("Thank you for Using the Grades System!\n");
            }
        } while (number != 3);

    }

    public static void enterGradesMenu() {
        int number = 0;

        do {
            System.out.println("\n===== ENTER GRADES =====");
            System.out.println("""
                    [1] Enter Grades
                    [2] Display Grades
                    [3] Exit
                        """);

            System.out.println("\nEnter Number: ");
            number = sc.nextInt();

            switch (number) {
                case 1:
                    enterGradesMenu();
                case 2:
                    displayGradesMenu();
                case 3:
                    System.out.println("Thank you for Using the Grades System!");
            }
        } while (number != 3);

    }

    public static void displayGradesMenu() {

    }
}