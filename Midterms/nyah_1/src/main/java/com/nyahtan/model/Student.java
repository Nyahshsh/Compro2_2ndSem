package com.nyahtan.model;

public class Student {
    String fullName;
    int age;
    String course;

    public Student(String studentFullName, int studentAge, String studentCourse){
        fullName = studentFullName;
        age = studentAge;
        course = studentCourse;
    }

    public Student() {
    }

    void printStudentDetails() {
        System.out.printf("""
                Student: %s
                Age: %s
                Course: %s\n
                """, fullName, age, course);
    }

}
