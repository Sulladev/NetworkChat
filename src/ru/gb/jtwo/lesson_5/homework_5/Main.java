package ru.gb.jtwo.lesson_5.homework_5;


import java.util.Arrays;

public class Main {

    static final int size = 10000000;
    static final int h = size/2;


    public static void main(String[] args) {
        float[] arr = new float[size];
        workingTime(arr);
        multithreadingWorkingTime(arr);
    }


    public static void workingTime ( float[] arr) {
        Arrays.fill(arr, 1);
        long currentTime = System.currentTimeMillis();

        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long newTime = System.currentTimeMillis();
        System.out.println("Single-threaded time = " + (newTime - currentTime) + " ms");
    }

    public static void multithreadingWorkingTime (float[] arr) {
        Arrays.fill(arr, 1);
        float[]arr1 = new float[h];
        float[]arr2 = new float[h];

        long currentTime = System.currentTimeMillis();

        System.arraycopy(arr,0,arr1,0,h);
        System.arraycopy(arr,0,arr2,0,h);

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <arr1.length ; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <arr2.length ; i++) {
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        };
        Thread thread2 = new Thread(task2);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long newTime = System.currentTimeMillis();
        System.out.println("Multithreaded time = " + (newTime - currentTime) + " ms");

    }
}
