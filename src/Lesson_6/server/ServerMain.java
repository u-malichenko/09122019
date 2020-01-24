package Lesson_6.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerMain {
    private Vector<ClientHandler> clients;

    public ServerMain() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();//сделаем подключение к базе
            //работа с базой только на сервере!
            //на клиенте только запросы!

//            String str = AuthService.getNickByLoginAndPass("login1", "pass1");
//            System.out.println(str);
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
                //подписка в векторлист переехала в конструктор клиентхендлера, тут просто создаем новый объект клиентхендлер
                // clients.add(new ClientHandler(this, socket));
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
            AuthService.disconnect(); //отключение от базы
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void sendPersonalMsg(ClientHandler from, String msg) {
        String[] tokes = msg.split(" ", 3);
        for (ClientHandler o : clients) {
            if (tokes[1].equals(o.getNick())) {
                o.sendMsg("from " + from.getNick() + ": " + tokes[2]);
                from.sendMsg("to " + o.getNick() + ": " + tokes[2]);
                return;
            }
        }
        from.sendMsg("клиент с ником - " + tokes[1] + " не найден");
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }
}
