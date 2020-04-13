package ru.gb.jtwo.lesson_3.homework_3;


import java.util.HashMap;


public class PhoneBook {


    HashMap<String, Person> phoneBook = new HashMap<>();

    public PhoneBook(HashMap<String, Person> phoneBook) {

        this.phoneBook = phoneBook;
    }

    public void addContact(String surname, Person person) {
        phoneBook.put(surname, person);
    }
}




