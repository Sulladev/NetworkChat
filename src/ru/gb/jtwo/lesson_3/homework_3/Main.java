package ru.gb.jtwo.lesson_3.homework_3;

import java.util.*;

public class Main {

    private static final String[] words = {"революция", "цель", "король", "низложен", "революция", "катящийся",
            "шар", "умеренные", "народ", "коммуна", "страх", "цель", "народ", "революция", "отстранить",
            "символ", "символ", "коммуна", "сжечь", "прах"};

    public static void main(String[] args) {

        System.out.println(getWords(words));
        System.out.println(getWordsCount(words));

        PhoneBook phonebook = new PhoneBook();
        phonebook.add("Ivanov", "ivanov-phone-1", "ivanov-mail-1");
        phonebook.add("Petrov", "petrov-phone-1", "petrov-mail-1");
        phonebook.add("Ivanov", "ivanov-phone-2", "ivanov-mail-2");

        System.out.println("e-mail Ivanov: " + phonebook.getMails("Ivanov"));
        System.out.println("e-mail Petrov: " + phonebook.getMails("Petrov"));
        System.out.println("phone Ivanov: " + phonebook.getPhones("Ivanov"));
        System.out.println("phone Petrov: " + phonebook.getPhones("Petrov"));

//        Set<String> mySet = new HashSet<>();
//        Collections.addAll(mySet,ArrayOfStrings);
//        System.out.println(mySet);
//        countEachMapWord(ArrayOfStrings);
    }


    private static TreeSet<String> getWords(String[] arr) {
        return new TreeSet<>(Arrays.asList(arr));
    }

    private static HashMap<String, Integer> getWordsCount(String[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String word = arr[i];
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        return map;
    }




//    public static  Map<String, Integer> countEachMapWord (String[] array) {
//
//        Map<String,Integer > map = new HashMap<>();
//        for (int i = 0; i <ArrayOfStrings.length ; i++) {
//            if(map.containsKey(ArrayOfStrings[i])) {
//                map.put(ArrayOfStrings[i], map.get(ArrayOfStrings[i]) +1);
//            } else {
//                map.put(ArrayOfStrings[i], 1);
//            }
//        }
//
//        for (Map.Entry<String,Integer> pair: map.entrySet()) {
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//        }
//
//        return map;
//    }

}
