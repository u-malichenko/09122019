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
                            /**
                             * TODO вставить ветку по косойчерте для сервисных сообщений
                             */
                            if (str.startsWith("/auth")) { //смотрим с чего начинаетс строка
                                String[] tokes = str.split(" "); //берем массив и делаем нашу строчку делаем сплит по пробелу
                                //запускаем метод с логином и паролем
                                String newNick = AuthService.getNickByLoginAndPass(tokes[1], tokes[2]);

                                //если соотвествующих записей не найдется то вернется НУЛЛЛ, там так написан код
                                //проверяем чтоб не вернулось нулл
                                /**
                                 * TODO Вставить проверку что юзер уже залогинен!
                                 */
                                if (newNick != null) {
                                    //если не равно нулу то авторизвались правильно, отправляем аутОК
                                    sendMsg("/authok");
                                    nick = newNick; //присваиваем то что вернул метод из аутентификации = имя текущего пользователя для подставноки в сообщения
                                    server.subscribe(ClientHandler.this); //делаем подписку, добавляем в векторлист объект
                                    break; //выходим из цыкла для начала работы с базой
                                } else {
                                    sendMsg("Неверный логин/пароль");
                                }
                            }
                        }

                        //цикл работы
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            System.out.println("Client: " + str);
                            server.broadcastMsg(nick + ": " + str); //добавили имя пользователя вк сообщению
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

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
