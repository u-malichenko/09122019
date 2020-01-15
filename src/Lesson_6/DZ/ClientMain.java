package Lesson_6.DZ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream in;
        DataOutputStream out;
        final String IP_ADDRESS = "localhost";
        final int PORT = 8189;

        try {
            socket = new Socket(IP_ADDRESS,PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String str = in.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                            //break;
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
