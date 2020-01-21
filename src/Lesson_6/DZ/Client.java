package Lesson_6.DZ;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static Socket clientSocket;
    private static Scanner consoleRead; // нам нужен ридер читающий с консоли
    private static Scanner in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost", 9999);

            consoleRead = new Scanner(System.in);
            in = new Scanner(clientSocket.getInputStream());
            out =  new PrintWriter(clientSocket.getOutputStream(), true);

            //READ ждем сообщения с сервера
            Thread clientRead = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine(); // ждем сообщения с сервера
                        if (str.equals("/exit")) {
                            out.println(str);//отправляем закрытие клиента знак серверу для закрытия
                            System.out.println("Server exit");
                            break; // выходим из цикла если пришло "exit"
                        }
                        System.out.println("Server: " + str); // пишем сообщение с сервера на консоль
                    }
                }
            });
            clientRead.start();

            // WRITE отправляем сообщения с консоли на сервер
            Thread clientWrite = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Введите сообщение:");
                        String str = consoleRead.nextLine();// сообщения с консоли
                        out.println(str); // отправляем на сервер
                    }
                }
            });
            clientWrite.setDaemon(true);
            clientWrite.start();

            try {
                clientRead.join();
            } catch (InterruptedException e) {
                System.out.println("error join");
                e.printStackTrace();
            }


        } catch (IOException e) {
            System.out.println("error main!");
            e.printStackTrace();
        } finally { // в любом случае сокет будет закрыт
            try {
                in.close();
                System.out.println("in close finally");
                out.close();
                System.out.println("out close finally");
                clientSocket.close();
                System.out.println("socket close finally");
            } catch (IOException e) {
                System.out.println("finally socket error");
                e.printStackTrace();
            }
        }
    }
}

