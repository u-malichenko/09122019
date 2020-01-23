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
    private String nick; //ник

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
                        //первый цикл по авторизации, вротой не начнется пока не пройдет этот
                        while (true) {
                            String str = in.readUTF(); //читаем поток в цикле

                            if (str.startsWith("/auth")) { //смотрим с чего начинаетс строка
                                String[] tokes = str.split(" "); //берем массив и делаем нашу строчку делаем сплит по пробелу
                                //запускаем метод с логином и паролем
                                String newNick = AuthService.getNickByLoginAndPass(tokes[1], tokes[2]);
                                //если соотвествующих записей не найдется то вернется НУЛЛЛ, там так написан код
                                //проверяем чтоб не вернулось нулл
                                if (newNick != null && !server.isNickBusy(newNick)) {
                                    //если не равно нулу то авторизвались правильно, отправляем аутОК
                                    sendMsg("/authok");
                                    nick = newNick; //присваиваем то что вернул метод из аутентификации = имя текущего пользователя для подставноки в сообщения
                                    server.subscribe(ClientHandler.this); //делаем подписку, добавляем в векторлист объект
                                    break; //выходим из цыкла для начала работы с базой
                                } else if (server.isNickBusy(newNick)) {
                                    sendMsg(tokes[1] + " Этот логин уже занят!");
                                } else {
                                    sendMsg("Неверный логин/пароль");
                                }
                            }
                        }

                        //цикл работы
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/")) {
                                if (str.equals("/end")) { //завершение работы чата
                                    out.writeUTF("/serverClosed");
                                    break;
                                }
                                if (str.startsWith("/w")) { //отправка личного сообщения «/w nick3 Привет»
                                    server.sendPersonalMsg(ClientHandler.this, str); //добавили имя пользователя вк сообщению
                                }
                            } else { //отправим сообщение сразу всем
                                System.out.println(nick + ": " + str);
                                server.broadcastMsg(nick + ": " + str); //запускаем метод на сервере
                                // добавили имя пользователя вк сообщению
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
