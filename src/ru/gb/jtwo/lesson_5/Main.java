package ru.gb.jtwo.lesson_5;

public class Main {
    private static long a;
    private static long b;
    private static long c;
    private static long a1;
    private static long b1;
    private static long c1;
    private static Object mon;

    // непотокобезопасный код
    private synchronized static void incAll() {
        for (int i = 0; i < 1000000; i++) {
            a = a + 1;
            b = b + 1;
            c = c + 1;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a, b, c);
        System.out.println(vars);
    }

    private static void incAll2() {
        synchronized (mon) {
            for (int i = 0; i < 1000000; i++) {
                a1 = a1 + 2;
                b1 = b1 + 2;
                c1 = c1 + 2;
            }
            String vars = String.format("a=%d, b=%d, c=%d", a1, b1, c1);
            System.out.println(vars);
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                incAll();
                incAll2();
                System.out.println("exited incall");
            }
        };
        new Thread(r, "Thread #1").start();
        new Thread(r, "Thread #2").start();
        new Thread(r, "Thread #3").start();

    }

    private static void threading() {
        // img
        // set filter
        MyThread t0 = new MyThread(1);
        MyThread t1 = new MyThread(2);
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        };
//        Thread t0 = new Thread(r);
//        t0.start();

        try {

            t0.join(); // while (t.isAlive()) {}
            System.out.println("sec");

            t1.join(); // while (t.isAlive()) {}
            System.out.println("two sec");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // return to user
        System.out.println("5");
    }
}
