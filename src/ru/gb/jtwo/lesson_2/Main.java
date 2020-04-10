package ru.gb.jtwo.lesson_2;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    private interface KeyboardListener {
        void keyPressed();
    }

    private static void addKeyboardListener(KeyboardListener listener) {

    }

    private static int integerDivision(int divisible, int divider) throws ArithmeticException {
        return divisible / divider;
    }

    public static void main(String[] args) {
        //integerDivision(5, 0);

        try (IOStream ios = new IOStream()) {
            ios.open();
            ios.write();
        } catch (SQLException | IOException e) {
            System.out.println("go ahead");
        }

        System.out.println("Main continues!");

    }

    private static class IOStream implements Closeable {
        public void open() throws FileNotFoundException {
            //throw new FileNotFoundException("File not found");
            System.out.println("open");
        }

        public void write() throws SQLException {
            throw new SQLException("No DB to write to");
            //System.out.println("write");
        }

        @Override
        public void close() throws IOException {
            throw new IOException("didn't close((((");
            //System.out.println("close");
        }
    }

    private static void interfaceExample() {
        class KeyboardHandler implements KeyboardListener {
            @Override
            public void keyPressed() { }
        }
        KeyboardHandler k = new KeyboardHandler();
        addKeyboardListener(k);
        addKeyboardListener(new KeyboardHandler());
        addKeyboardListener(new KeyboardListener() {
            @Override
            public void keyPressed() { }
        });
        addKeyboardListener( () -> {

        });
    }

    private static void minotaurExample() {
        Minotaur m = new Minotaur();

        Human[] people = { m };
        Bull[] zoo = { m };

        people[0].think();
        zoo[0].voice();
        zoo[0].breathe();
        people[0].look();
    }
}
