package ru.gb.jtwo.lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    // многострочная отображалка текста. общий журнал чата
    private final JTextArea log = new JTextArea();
    // панель на которой мы собираем компоненты сеткой из двух строчек и трёх столбцов
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    //  однострочное текстовое поле с IP
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    //  однострочное текстовое поле с Port
    private final JTextField tfPort = new JTextField("8189");
    // компонент "галочка"
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JTextField tfLogin = new JTextField("ivan");
    // компонент для пороля. маскированное поле (****)
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    // текст филд куда мы будем вводить наше сообщение
    private final JTextField tfMessage = new JTextField();
    private final static String newline = "\n";
    private final JButton btnSend = new JButton("Send");

    // добавляем Лист юзеров
    private final JList<String> userList = new JList<>();
    // если хоть раз показали эксепшн - больше ему не показываю
    private boolean shownIOErrors = false;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    new ClientGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    ClientGUI() throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setAlwaysOnTop(true);
        // сходу String не добавить.Поэтому действуем через setListData с массивом строк
        userList.setListData(new String[]{"user1", "user2", "user3", "user4",
                "user5", "user6", "user7", "user8", "user9",
                "user-with-exceptionally-long-name-in-this-chat"});
        // компонент скролла для юзеров
        JScrollPane scrUser = new JScrollPane(userList);
        // компонент скролла для тект эрии
        JScrollPane scrLog = new JScrollPane(log);
        // устанавливаем предпочтительный размер юзер зоны для переносов длинных имён
        scrUser.setPreferredSize(new Dimension(100, 0));
        // работаем с текст эрией или логами
        log.setLineWrap(true);
        // перенос строк по правилам (по словам)
        log.setWrapStyleWord(true);
        // отменяем возможность редактировать поле с логами
        log.setEditable(false);
        cbAlwaysOnTop.addActionListener(this);
        tfMessage.addActionListener(this);
        btnSend.addActionListener(this);

        // добавляем на панель компоненты
        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        // добавляем панели на окошко
        add(scrUser, BorderLayout.EAST);
        add(scrLog, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        setVisible(true);

        File file = new File("log.txt");
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);

        FileReader fileReader = new FileReader(file);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(log.getText());
        bufferedWriter.flush();
        bufferedWriter.close();

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()){
            System.out.println(bufferedReader.readLine());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        // реализуем нажатие галочки
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == tfMessage || src == btnSend){
            sendMessage();
        } else {
            throw new RuntimeException("Unknown source:" + src);
        }
     }

     // метод, который лушче вынести наружу и дёргать в любой ситуации
     private void sendMessage () {
        String msg = tfMessage.getText();
        String userName = tfLogin.getText();
        if("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        //будет неплохо расделать складывание в лог и складывание в файл. если наддоест логировать
         // в файл можно просто закомменттировать метод
        putLog(String.format("%s: %s", userName, msg));
        wrtMsgToLogFile(msg, userName);
     }

     private void wrtMsgToLogFile(String msg, String username){
        // открываю FWr, записываю в log.txt какие-то сведения
        try (FileWriter out = new FileWriter("log.txt", true)){
            out.write(username + ":  " + msg + System.lineSeparator());
            out.flush();
        } catch (IOException e) {
            if (!shownIOErrors) {
                shownIOErrors = true;
                showException(Thread.currentThread(), e);
            }
        }

     }
        //метод предназначен не только для для sendMessage
        //в потоке EventDispatchingThread вызываю методы лога, добавляю msg и тд
     private void putLog(String msg) {
        if ("".equals(msg)) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + System.lineSeparator());
                log.setCaretPosition(log.getDocument().getLength());
            }
        });


     }

     private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0) {
            msg = " Empty Stacktrace";
        } else {
            msg = String.format("Exception in \"%s\" %s: %s\n\tat %s",
                    t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
            //JOptionPane - класс, который отвечает за разные модальные (диалоговые) окошечки
            JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        }
     }



    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showException(t,e);
        System.exit(1);
    }

}
