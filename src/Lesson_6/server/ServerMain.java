package Lesson_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerMain {
    private Vector<ClientHandler> clients;

    public Vector<ClientHandler> getClients() {
        return clients;
    }

    public ServerMain() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                clients.add(new ClientHandler(this, socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) { //перебирает список всех клиентов и вызывает у каждого метод отправки сообщения
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }
}
