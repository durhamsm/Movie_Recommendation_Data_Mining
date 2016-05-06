package com.company.Users;

public class User {
    private int id;
    private int age;
    private GENDER gender;
    private OCCUPATION occupation;
    private String zipCode;

    public User(String[] fieldValues) {
        id = Integer.valueOf(fieldValues[0]);
        age = Integer.valueOf(fieldValues[1]);
        gender = GENDER.valueOf(fieldValues[2].toUpperCase());
        occupation = OCCUPATION.valueOf(fieldValues[3].toUpperCase());
        zipCode = fieldValues[4];
    }

    enum GENDER {
        M, F
    }

    enum OCCUPATION {
        ADMINISTRATOR, ARTIST, DOCTOR, EDUCATOR, ENGINEER,
        ENTERTAINMENT, EXECUTIVE, HEALTHCARE, HOMEMAKER, LAWYER, LIBRARIAN,
        MARKETING, NONE, OTHER, PROGRAMMER, RETIRED, SALESMAN, SCIENTIST,
        STUDENT, TECHNICIAN, WRITER
    }

}