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
            WorkDB.connect();//сделаем подключение к базе
            //работа с базой только на сервере!
            //на клиенте только запросы!
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
                //подписка в векторлист переехала в конструктор клиентхендлера, тут просто создаем новый объект клиентхендлер
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
            WorkDB.disconnect(); //отключение от базы
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    public void sendPersonalMsg(ClientHandler from, String msg) {
        String[] tokens = msg.split(" ", 3);
        for (ClientHandler o : clients) { //цикл обхода всех пользователей
            if (tokens[1].equals(o.getNick()) && !o.checkBlackList(from.getNick())) { //если ник из сообщения пользователя совпадает с ником данногоо клиента,
                // и у этого пользователя в блеклисте нет пользователя который отправляет
                o.sendMsg("from " + from.getNick() + ": " + tokens[2]); //отправляем сообщеине этому клиенту
                from.sendMsg("to " + o.getNick() + ": " + tokens[2]); //отправляем сообщеине пльзователю который отпралял
                return;
            }
        }
        from.sendMsg("клиент с ником - " + tokens[1] + " не найден, или вы занесены в его черный список. и не можете отправлять ему сообщения.");
        // если ник не найден, пишем пользователю который отправлял что нет такого
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastMsg(ClientHandler from, String msg) { //пришел пользователь, от кого мы собираемся отправить сообщение и сообщение
        for (ClientHandler o : clients) { //перебираем список всех клиентов
            if (!o.checkBlackList(from.getNick())) //проверяем, у каждого клиента мы смотрим черный список, и
                // если нашего пользователя (от кого сообщение) нету в черном списке
            o.sendMsg(msg); //то тогда мы отправляем сообщение, иначе сообщение не отправляем
        }
    }

    /**
     * метод который собирает список всех клиентов и отправляет их на сервер
     */
    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder(); //тот же стирнг только экономичнее, изменяемый
        // строка имутабельный обект в памяти
        sb.append("/clientlist "); //сначала добавляем служебное сообщение!!!!!
        for (ClientHandler o : clients) { //перебор списка всех наших клиентов
            sb.append(o.getNick() + " "); //добавление ника пользователя в список для отправки
        }
        String out = sb.toString(); // преобраховываю в одну строку исходящее сообщение = собраная строка в стрингбилдере
        for (ClientHandler o: clients) { //перебор списка всех наших клиентов
            o.sendMsg(out); //отправить готовую строку с новым списком пользователй
        }
    }
}
