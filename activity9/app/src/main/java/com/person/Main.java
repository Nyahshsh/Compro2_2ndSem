package com.person;

import com.google.gson.Gson;
import com.person.data.Person;

public class Main {
    public static void main(String[] args){
        String json = "";
        json = """
                {
                "First Name": "Louie",
                "Last Name": "Bituan",
                "Age": 35,
                "Email Address": "imafem@lorma.edu",
                "Phonenumber": "09878908674",
                "Date of Birth": "February 11, 1999",
                "Home Address": "Paruparu",
                "Employment Status":  true,
                "Nationality": "Filipino",
                "Gender": "Femboy"
                }
                """;

    Gson gson = new Gson();
    Person person = gson.fromJson(json, Person.class);
    System.out.println(person.toString());
}