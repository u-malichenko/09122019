package Lesson_6.DZ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            socket= server.accept();
            System.out.println("Клиент подключился");

            //Scanner sc = new Scanner(socket.getInputStream()); //сканер
            DataInputStream in = new DataInputStream(socket.getInputStream());//входящий поток
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); //исходящий поток
//            Scanner in = new Scanner(socket.getInputStream());
//            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true){
                String str = in.readUTF();
                if(str.equals("/end")){
                    break;
                }
                System.out.println("Клиент: " + str);
                out.flush(); //метод говорит что сообщение полное и готово к отправке
                out.writeUTF(str); //отправить сообщение клиенту
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
}
