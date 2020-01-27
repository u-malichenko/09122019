package Lesson_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

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
                                String newNick = WorkDB.getNickByLoginAndPass(tokes[1], tokes[2]);
                                //если соотвествующих записей не найдется то вернется НУЛЛЛ, там так написан код
                                //проверяем чтоб не вернулось нулл
                                if (newNick != null && !server.isNickBusy(newNick)) {
                                    //если не равно нулу то авторизвались правильно, отправляем аутОК
                                    sendMsg("/authok " + newNick);
                                    //sendMsg("/authok");
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
                            String currentNick = ClientHandler.this.getNick();
                            String str = in.readUTF();

                            //СЛУЖЕБНЫЙ БЛОК
                            if (str.startsWith("/")) {
                                if (str.equals("/end")) { //завершение работы чата
                                    out.writeUTF("/serverClosed");
                                    break;
                                }
                                if (str.startsWith("/w")) { //отправка личного сообщения «/w nick3 Привет»
                                    server.sendPersonalMsg(ClientHandler.this, str); //добавили имя пользователя вк сообщению
                                }
                                if (str.startsWith("/blacklist ")) { //черный список
                                    String[] tokens = str.split(" ");
                                    if(!currentNick.equals(tokens[1])){
                                        String resultAddBlockList = WorkDB.addBlackList(currentNick, tokens[1]);
                                        sendMsg(resultAddBlockList); // сообщение пользователю с результатом добавления
                                    }else{
                                        sendMsg("Извините, к сожалению не возможно добавить в черный список самого себя!"); // иначе сообщение об ошибке
                                    }
                                }
                            } else { //отправим сообщение сразу всем
                                System.out.println(nick + ": " + str);
                                server.broadcastMsg(ClientHandler.this, nick + ": " + str); //запускаем метод на сервере. отправляем этого пользователя и сообщение
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

    /**
     * метод проверки по нику
     * наличие у пользователя в блеклисте этого пользователя
     * @param nick
     * @return ДА, есть. или НЕТ
     */
    public boolean checkBlackList(String nick) {
        //получаем список заблокированных ников
        List<String> blackList = WorkDB.getBlackListNickByThisNick(ClientHandler.this.getNick());
        return blackList.contains(nick);
    }

    public void sendMsg(String str) {
        try {
            out.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
