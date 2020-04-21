package ru.gb.jtwo.lesson_5.homework_5;

import java.util.Arrays;

public class MainIvan {

    static final int SIZE = 10000000;
    static final int HALF_SIZE = SIZE/2;
    static float[] arrOne = new float[SIZE];
    static float[] arrTwo = new float[SIZE];


    public static void main(String[] args) {

        Arrays.fill(arrOne, 1f);
        Arrays.fill(arrTwo, 1f);

        long beginTime = System.nanoTime();
        calculateArray(arrOne);
        long deltaTime = System.nanoTime() - beginTime;
        System.out.println("One thread time: " + deltaTime * 1e-9f );

        beginTime = System.nanoTime();
        calculateArrayTwoThreads(arrTwo);
        deltaTime = System.nanoTime() - beginTime;
        System.out.println("Two thread time: " + deltaTime * 1e-9f );

        if (Arrays.equals(arrOne, arrTwo)) {
            System.out.println("Arrays are equal");
        } else  {
            System.out.println("Arrays are not equal");
        }

    }

    private static void calculateArray (float[] arr) {
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = (float)(arr[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));

        }
    }

    public static void calculateArrayTwoThreads (float[] arr) {
        final float[] a1 = new float[HALF_SIZE];
        final float[] a2 = new float[HALF_SIZE];

        //System.arraycopy(массив-источник, откуда начинаем брать данные из
        // массива-источника,
        // массив-назначение, откуда начинаем записывать данные в
        // массив-назначение, сколько ячеек копируем)

        System.arraycopy(arr, 0, a1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, a2, 0, HALF_SIZE);

        // CalcThread - универсальный Thread, потому что мы можем захотеть
        // поделить массив на 4 или 8 састей и тогда предётся писать кучу Runnable
        CalcThread threadOne = new CalcThread(a1,0);
        CalcThread threadTwo = new CalcThread(a2, HALF_SIZE);

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);
    }
}
