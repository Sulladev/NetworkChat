package ru.gb.jtwo.lesson_2.homework;

import java.util.Arrays;



public class Main {
    public static void main(String[] args) {

        stringToArray("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0");


    }

    public static void  stringToArray (String string)  {

        string = string.replace(" ", ",");
        System.out.println(string);
        String[] oneDimensionalArray = string.split("\n");

        System.out.println(Arrays.toString(oneDimensionalArray));
        String[][] twoDimensionalArray = new String[oneDimensionalArray.length][oneDimensionalArray.length];

        for (int i = 0; i <oneDimensionalArray.length ; i++) {
            oneDimensionalArray[i] = oneDimensionalArray[i].trim();
            String [] singleInt = oneDimensionalArray[i].split(",");
            for (int j = 0; j <singleInt.length ; j++) {
                twoDimensionalArray[i][j]=singleInt[j];

            }
        }

        System.out.println(Arrays.deepToString(twoDimensionalArray));
        System.out.println(twoDimensionalArray[0][0]);
    }

    public static void stringArrayToInt (String [][] stringArray){

        int[][] intArray = new int[stringArray.length][stringArray[0].length];
        for (int i = 0; i <stringArray.length ; i++) {
            for (int j = 0; j <stringArray.length ; j++) {
                intArray[i][j] = Integer.parseInt(stringArray[i][j]);
            }

        }

        System.out.println(Arrays.deepToString(intArray));

        int sumOfNumbers = 0;
        for (int i = 0; i <intArray.length ; i++) {
            for (int j = 0; j <intArray.length ; j++) {
                sumOfNumbers += intArray[i][j];
            }

        }

        System.out.println(sumOfNumbers);

        int result = sumOfNumbers/2;


    }

}
