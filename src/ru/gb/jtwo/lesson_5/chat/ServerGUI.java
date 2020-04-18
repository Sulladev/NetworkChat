package ru.gb.jtwo.lesson_5.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler  {
    //задаём размеры окна сервера
    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;

    // в нашем окошке находится старт программы. при старте мы должны создавать объект сервера,
    // чтобы с ним потом взаимодействовать. Создаём кнопки Старт и Стоп
    private final ChatServer server;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");

    public static void main(String[] args) {
        // эта конструкция (invLater) реализует интерфейс Runn, который выделяет выполненной  в нём в отдельный поток
        //отдельная нить выполнения идёт параллельно с main и не заввисимо от main
        // Метод invlater делает этот поток конкретным EDT (event dispatching thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
//        throw new RuntimeException("Main died!");
        System.out.println("Main finished");
    }

    ServerGUI() {
        //почти все фреймворки позволяют создавать свои обработчики исключений для потока
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        //добавляем сетку с одной строкой и двумя колонками. добавляемм кнопку Старта и Стопа
        setLayout(new GridLayout(1, 2));
        // к кнопкам добавляем поведение
        btnStart.addActionListener(this);
        //передаем объект текущего класса который имплементит ActionListner
        btnStop.addActionListener(this);

        add(btnStart);
        add(btnStop);
        server = new ChatServer();
        setVisible(true);
    }

    // реализуем метод интерфейса ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnStart) {
            server.start(8189);
        } else if (src == btnStop) {
            server.stop();
            throw new RuntimeException("Hello from EDT!");
        } else {
            throw new RuntimeException("Unknown source:" + src);
        }
    }

    //эксэпшны возникающие на графическом интерфейсе - графический интерфейс не роняют
    //почти все фреймворки позволяют создавать свои обработчики исключений для потока
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //обрабатываем эксепшн на своих условиях
        e.printStackTrace();
        String msg;
        // у всех исключений есть информативный стектрейс, который говорит что именно, где именно произошло
        //такой стектрейс состоит из массивов стектрейс элементов
        StackTraceElement[] ste = e.getStackTrace();
        msg = String.format("Exception in \"%s\" %s: %s\n\tat %s",
                t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        //JOptionPane - класс, который отвечает за разные модальные (диалоговые) окошечки
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        //Скажем, что наша программа далеене имеет смысла в исполнении
        System.exit(1);
    }
}
