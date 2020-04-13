package ru.gb.jtwo.lesson_3.homework_3;


public class Person {

    private String phoneNumber;
    private String email;

    public Person(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}

