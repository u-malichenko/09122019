package Lesson_6.DZ;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader inputUser;
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            server = new ServerSocket(9999); // серверсокет прослушивает порт 9999
            System.out.println("Сервер запущен! Тепрь запустите клиента.");
            clientSocket = server.accept(); // accept() будет ждать конекта от клинета

            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            //READ ждем сообщения от клиента
            Thread serverRead = new Thread(new Runnable() {
                @Override
                public void run() {
                    String str;
     ///////////////////////////////////////////////////
                    try {
     ////////////////////////////////////////////////////////
                        while (true) {
                            str = in.readLine(); // ждем сообщения от клиента
                            if (str.equals("exit")) {
                                out.write(str);//отправляем закрытие сервера знак клиенту для закрытия
                                out.flush(); // чистим
                                System.out.println("Client go out(");
                                break;
                            }
                            System.out.println("Client: " + str); // пишем сообщение с сервера на консоль
                        }
  ///////////////////////////////////////////////////////////////
                    } catch (IOException e) {
                        System.out.println("serverRead");
                        e.printStackTrace();
                    }
  ///////////////////////////////////////////////////////
                }
            });
            serverRead.start();

            // WRITE отправляем сообщения с консоли на клиент
            Thread serverWrite = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String userWord;
  /////////////////////////////////////////////////////
                        try {
   ///////////////////////////////////////////////////
                            System.out.println("Введите сообщение:");
                            userWord = inputUser.readLine(); // сообщения с консоли
                                out.write("Server: " + userWord + "\n"); // отправляем клиенту
                                out.flush(); // чистим
 ////////////////////////////////////////////////////
                        } catch (IOException e) {
                            System.out.println("serverWrite");
                            e.printStackTrace();
                            break;
                        }
 ///////////////////////////////////////////////////////////
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

            try {
                System.out.println("Сервер закрыт2! in finally");
                server.close();
            } catch (IOException e1) {
                System.out.println("finally server close error");
                e1.printStackTrace();
            }
        }
    }
}