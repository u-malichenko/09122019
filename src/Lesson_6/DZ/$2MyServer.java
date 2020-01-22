package Lesson_6.DZ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class $2MyServer {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static Scanner consoleRead;
    private static Scanner in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            server = new ServerSocket(9999); // серверсокет прослушивает порт 9999
            System.out.println("Сервер запущен! Тепрь запустите клиента.");

            clientSocket = server.accept(); // accept() будет ждать конекта от клинета
            System.out.println("Клиент подключился!");

            consoleRead = new Scanner(System.in);
            in = new Scanner(clientSocket.getInputStream());
            out =  new PrintWriter(clientSocket.getOutputStream(), true);

            //READ ждем сообщения от клиента
            Thread serverRead = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine(); // ждем сообщения от клиента
                        if (str.equals("/exit")) {
                            out.println(str);//отправляем закрытие сервера знак клиенту для закрытия
                            System.out.println("Client exit...");
                            break;
                        }
                        System.out.println("Client: " + str); // пишем сообщение с сервера на консоль
                    }
                }
            });
            serverRead.start();

            // WRITE отправляем сообщения с консоли на клиент
            Thread serverWrite = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Введите сообщение:");
                        String userWord = consoleRead.nextLine(); // сообщения с консоли
                        out.println(userWord); // отправляем клиенту
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

            try {
                System.out.println("Сервер закрыт! in finally");
                server.close();
            } catch (IOException e1) {
                System.out.println("finally server close error");
                e1.printStackTrace();
            }
        }
    }
}