package Lesson_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerMain server;

    public ClientHandler(ServerMain server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        while (true) {
                            String str = in.readUTF(); //клиент присылает сообщение мы его считываем
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed"); //отправить сообщение на клиент, чтоб он закрыл свой сокет тоже
                                break;
                            }
                            System.out.println("Client: " +Thread.currentThread().getName()+" "+ str);
                            server.broadcastMsg(str); //запуск метода на стороне сервера для отправки сообщения во все клиенты
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        del();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void del(){
        server.getClients().remove(this);

    }

    public void sendMsg(String str) { //метод для отправки сообщения конкретному клиенту
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
