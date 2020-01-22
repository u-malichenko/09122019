package Lesson_6.server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC"); //обращаемся к драйверу, инициализируем драйввер
            connection = DriverManager.getConnection("jdbc:sqlite:mydb.db"); //обращаемся к менеджеру
            // определить соединение, строка подключения к базе(полный путь если не в проекте)
            //через запятую указываем логин пароль если нужно Коннекшенстринг!
            //низкий уровень
            stmt = connection.createStatement(); //объект для создания запросов, высший уровень
            //отправляет запросы и возвращает результат
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        //формируем запрос к бд
        String sql = String.format("SELECT nickname FROM main where login = '%s' and password = '%s'", login, pass);

        try {
            ResultSet rs = stmt.executeQuery(sql); //объект принимает результатт запроса
            if (rs.next()) { //если есть строчка для обработки вернет тру
                return rs.getString(1); //обращаюсь к первому индексу, можно по имени
                //индексация в ДЖИДИБИСИ начинается с 1!!!
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; //если ни чего не нашли возвращаем нулл
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
