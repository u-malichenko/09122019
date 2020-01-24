package Lesson_6.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Controller {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP_ADPRESS = "localhost";
    final int PORT = 8189;

    @FXML
    HBox upperPanel; //верхняя панель для авторизации

    @FXML
    HBox bottomPanel; //нижняя спрятанная панель для работы самого чата

    @FXML
    TextField loginField; //

    @FXML
    PasswordField passwordField;

    private boolean isAuthorized; //флаг для проверки авторизации, для ченжа панелей

    /**
     * позволяет переключать панели, показывать нижнюю или показывать верхнюю панель
     * при входе и при выходе авторизации/деавторизации
     *
     * @param isAuthorized
     */
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized; //чтоб переменная имела одно занчение при переключениях
        if (!isAuthorized) { //если не фолс = тру если не тру = фолс
            upperPanel.setVisible(true); //поакзывать верхнюю паенль
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false); //не показывать нижнюю
            bottomPanel.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
        }
    }


    public void connect() {
        try {
            socket = new Socket(IP_ADPRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //цикл для авторизации, по аналогии с клиентхендлером, вротой не начнется пока не пройдет этот
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) { //если строка начинается с метки авторизации
                                setAuthorized(true); //запускаем метод для смены верхней и нижней панели с тру!
                                break;
                            } else {
                                textArea.appendText(str + "\n"); //отправляем сообщение в текстфилд
                                // для обозначения что логин пароль не верный, если он не верный
                            }
                        }

                        //цикл для работа
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) break;
                            textArea.appendText(str + "\n");
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
                        //при выходе запускаем метод и скрываем панель для работы чата
                        // и открываем вновь панель для ввода логина и пароля
                        setAuthorized(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод для проверки аутентификации подключение
     *
     * @param actionEvent
     */
    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) { // если сокета нет или он закрыт вызываем метод коннект
            connect(); // метод авторизации на сервере лежит там же авторизейшен сервис!
        }
        try {
            //отправка авторизации "/auth " все разделить пробелами для сплита
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());

            loginField.clear(); //чистим поля за собой, вдрег новые будут вводить
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
