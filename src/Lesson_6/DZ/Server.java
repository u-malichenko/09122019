package Lesson_6.DZ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader inputUser;
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    private Server() throws IOException {
        try { // установив связь и воссоздав сокет для общения с клиентом можно перейти
            // к созданию потоков ввода/вывода.
            // теперь мы можем принимать сообщения
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // и отправлять
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            Thread serverWrite = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String userWord;
                        try {
                            System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                            userWord = inputUser.readLine(); // сообщения с консоли
                            if (userWord.equals("exit")) { // харакири
                                try {
                                    clientSocket.close();
                                    in.close();
                                    out.close();
                                } catch (IOException ignored) {
                                }
                                break; // выходим из цикла если пришло "exit"
                            } else {
                                out.write("Server: " + userWord + "\n"); // отправляем на сервер
                            }
                            out.flush(); // чистим
                        } catch (IOException e) {
                            // в случае исключения тоже харакири
                            try {
                                clientSocket.close();
                                in.close();
                                out.close();
                            } catch (IOException ignored) {
                            }
                        }

                    }
                }
            });


            Thread serverRead = new Thread(new Runnable() {
                @Override
                public void run() {
                    String str;
                    try {
                        while (true) {
                            str = in.readLine(); // ждем сообщения от клиента
                            if (str.equals("exit")) {
                                try {
                                    clientSocket.close();
                                    in.close();
                                    out.close();
                                } catch (IOException ignored) {
                                }
                                break; // выходим из цикла если пришло "exit"
                            }
                            System.out.println(str); // пишем сообщение с сервера на консоль
                        }
                    } catch (IOException e) {
                        // в случае исключения тоже харакири
                        try {
                            clientSocket.close();
                            in.close();
                            out.close();
                        } catch (IOException ignored) {
                        }
                    }
                }
            });


            serverWrite.start();
            serverRead.start();

            serverRead.join();
            serverRead.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally { // в любом случае сокет будет закрыт
            System.out.println("socket close");
            clientSocket.close();
            // потоки тоже хорошо бы закрыть
            inputUser.close();
            in.close();
            out.close();
        }
    }

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(9999); // серверсокет прослушивает порт 9999
                System.out.println("Сервер запущен! Тепрь запустите клиента."); // хорошо бы серверу
                //   объявить о своем запуске
                clientSocket = server.accept(); // accept() будет ждать пока
                //кто-нибудь не захочет подключиться

                new Server();

            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}