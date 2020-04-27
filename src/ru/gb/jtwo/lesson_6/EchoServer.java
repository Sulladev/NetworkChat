package ru.gb.jtwo.lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)){
            while (true) {
                Socket s = serverSocket.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                System.out.println("Client Connected");
                while (true) {
                    String b = in.readUTF();
                    System.out.println("Client sent: " + b);
                    out.writeUTF("echo: " + b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
