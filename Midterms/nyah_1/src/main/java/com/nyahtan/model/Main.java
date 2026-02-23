package com.nyahtan.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== STUDENT DETAILS =====");

        Student s1 = new Student("Janiyah Tan", 18, "BSIT");
        Student s2 = new Student("Brianna Bustamante", 18, "BSIT");

        s1.printStudentDetails();
        s2.printStudentDetails();

        try (FileWriter fw = new FileWriter("data.csv")) {
            fw.write("Full Name,Age,Course\n");
            fw.write(s1.fullName + "," + s1.age + "," + s1.course + "\n");
            fw.write(s2.fullName + "," + s2.age + "," + s2.course + "\n");
            System.out.println(">>>Data saved to data.csv");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }
}