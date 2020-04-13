package ru.gb.jtwo.lesson_3.homework_3;

import java.util.*;

public class Main {

    private static String[] ArrayOfStrings = {"революция", "цель", "король", "низложен", "революция", "катящийся",
            "шар", "умеренные", "народ", "коммуна", "страх", "цель", "народ", "революция", "отстранить",
            "символ", "символ", "коммуна", "сжечь", "прах"};

    public static void main(String[] args) {

        Set<String> mySet = new HashSet<>();
        Collections.addAll(mySet,ArrayOfStrings);
        System.out.println(mySet);
        countEachMapWord(ArrayOfStrings);



    }

    public static  Map<String, Integer> countEachMapWord (String[] array) {

        Map<String,Integer > map = new HashMap<>();
        for (int i = 0; i <ArrayOfStrings.length ; i++) {
            if(map.containsKey(ArrayOfStrings[i])) {
                map.put(ArrayOfStrings[i], map.get(ArrayOfStrings[i]) +1);
            } else {
                map.put(ArrayOfStrings[i], 1);
            }
        }

        for (Map.Entry<String,Integer> pair: map.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }

        return map;
    }

}
