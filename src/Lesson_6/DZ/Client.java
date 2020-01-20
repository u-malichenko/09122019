package Lesson_6.DZ;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader inputUser; // нам нужен ридер читающий с консоли
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost", 9999);

            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            //READ ждем сообщения с сервера
            Thread serverRead = new Thread(new Runnable() {
                @Override
                public void run() {
                    String str;
                    try {
                        while (true) {
                            str = in.readLine(); // ждем сообщения с сервера
                            if (str.equals("exit")) {
                                out.write(str);//отправляем закрытие клиента знак серверу для закрытия
                                out.flush(); // чистим
                                System.out.println("Server go out(");
                                break; // выходим из цикла если пришло "exit"
                            }
                            System.out.println(str); // пишем сообщение с сервера на консоль
                        }
                    } catch (IOException e) {
                        System.out.println("serverRead");
                        e.printStackTrace();
                    }
                }
            });
            serverRead.start();

            // WRITE отправляем сообщения с консоли на сервер
            Thread serverWrite = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String userWord;
                        try {
                            System.out.println("Введите сообщение:");
                            userWord = inputUser.readLine();// сообщения с консоли
                                out.write("Client: " + userWord + "\n"); // отправляем на сервер
                                out.flush(); // чистим
                        } catch (IOException e) {
                            System.out.println("serverWrite");
                            e.printStackTrace();
                        }
                    }
                }
            });
            serverWrite.setDaemon(true);
            serverWrite.start();

            try {
                serverRead.join();
            }catch (InterruptedException e){
                System.out.println("error join");
                e.printStackTrace();
            }


        } catch (IOException e) {
            System.out.println("error main!");
            e.printStackTrace();
        } finally { // в любом случае сокет будет закрыт
            System.out.println("socket close finally");
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("finally socket error");
                e.printStackTrace();
            }
        }
    }
}

