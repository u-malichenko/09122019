package Lesson_6.DZ;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader inputUser; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static boolean close = false;

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт - 9999, такой же как у сервера
                clientSocket = new Socket("localhost", 9999); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                inputUser = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                // WRITE нить отправляющая сообщения приходящие с консоли на сервер
                Thread serverWrite = new  Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            String userWord;
                            try {
                                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                                userWord = inputUser.readLine();// сообщения с консоли
                                if (userWord.equals("exit")) { // харакири
                                   try {
                                        clientSocket.close();
                                        in.close();
                                        out.close();
                                   } catch (IOException ignored) {}
                                    break; // выходим из цикла если пришло "exit"
                                } else {
                                    out.write("Client: " + userWord + "\n"); // отправляем на сервер
                                }
                                out.flush(); // чистим
                            } catch (IOException e) {
                                 // в случае исключения тоже харакири
                                try {
                                    clientSocket.close();
                                    in.close();
                                    out.close();
                                } catch (IOException ignored) {}
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
                                str = in.readLine(); // ждем сообщения с сервера
                                if (str.equals("exit")) {
                                    try {
                                        clientSocket.close();
                                        in.close();
                                        out.close();
                                    } catch (IOException ignored) {}
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
                            } catch (IOException ignored) {}
                        }
                    }
                });

                serverWrite.start();
                serverRead.start();

                serverRead.join();
                serverRead.join();

            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                inputUser.close();
                in.close();
                out.close();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
    }
}

