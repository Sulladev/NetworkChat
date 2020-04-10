package ru.gb.jtwo.lesson_2.homework;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        String string = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
        string = string.replace(" ", ",");
//        System.out.println(string);
        String[] oneDimensionalArray = string.split("\n");

//        System.out.println(Arrays.toString(oneDimensionalArray));
        String[][] twoDimensionalArray = new String[oneDimensionalArray.length][oneDimensionalArray.length];
//        System.out.println(Arrays.deepToString(twoDimensionalArray));

        for (int i = 0; i <oneDimensionalArray.length ; i++) {
            oneDimensionalArray[i] = oneDimensionalArray[i].trim();
            String [] singleInt = oneDimensionalArray[i].split(",");
            for (int j = 0; j <singleInt.length ; j++) {
                twoDimensionalArray[i][j]=singleInt[j];
                
            }
        }


//        System.out.println(Arrays.deepToString(twoDimensionalArray));
//
//        System.out.println(twoDimensionalArray[0][0]);


        int[][] stringToInt = new int[twoDimensionalArray.length][twoDimensionalArray[0].length];
        for (int i = 0; i <twoDimensionalArray.length ; i++) {
            for (int j = 0; j <twoDimensionalArray.length ; j++) {
                stringToInt[i][j] = Integer.parseInt(twoDimensionalArray[i][j]);
            }

        }

        System.out.println(Arrays.deepToString(stringToInt));

        int sumOfNumbers = 0;
        for (int i = 0; i <stringToInt.length ; i++) {
            for (int j = 0; j <stringToInt.length ; j++) {
                sumOfNumbers += stringToInt[i][j];
            }

        }

        System.out.println(sumOfNumbers);

        int result = sumOfNumbers/2;


    }
}
