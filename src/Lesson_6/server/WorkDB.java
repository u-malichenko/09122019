package Lesson_6.server;

import java.sql.*;
import java.util.ArrayList;

public class WorkDB {
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

    /**
     * получение ника по логину и паролю
     *
     * @param login
     * @param pass
     * @return
     */
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

    /**
     * Метод добычи серного списка клиента
     * @param nick
     * @return
     */
    public static ArrayList<String> getBlackListNickByThisNick(String nick) {
        ArrayList<String> result = new ArrayList<>();
        String id_user = getIdByNick(nick);
        String sql = String.format("SELECT nickname FROM main INNER JOIN blacklist ON blacklist.id_block_user = main.id WHERE blacklist.id_user = %s", id_user);
        try {
            ResultSet rs = WorkDB.stmt.executeQuery(sql); //объект принимает результатт запроса и выполняет его в БД
            while (rs.next()) { //цикл работает пока есть следующий элемент в ответе запроса
                result.add(rs.getString(1)); //добавляем в арэйлист всех кого выдал запрос
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод добавления в блоклист
     * @param nick
     * @param blockNick
     * @return
     */
    public static String addBlackList(String nick, String blockNick) {
        String result;
        String id_user = getIdByNick(nick);
        String id_block_user = getIdByNick(blockNick);
        if (id_block_user == null) {
            result = "Извините, пользователя " + blockNick + " нет в бвзе.";
        } else {
            String sql = String.format("INSERT INTO blacklist(id_user, id_block_user) VALUES ('%s', '%s')", id_user, id_block_user);
            try {
                /**
                 * TODO удрать возможные посторы уже существующих записей - дублирование строк в базе
                 */
                WorkDB.stmt.executeUpdate(sql); //запрос на обновление, выполняет в БД
            } catch (SQLException e) {
                e.printStackTrace();
            }
            result = "Вы добавили пользователя " +blockNick + " в черный список!";
        }
        return result;
    }

    /**
     * Метод добычи из базы ID по нику
     *
     * @param nick
     * @return
     */
    public static String getIdByNick(String nick) {
        String sql = String.format("SELECT id FROM main WHERE nickname = '%s'", nick);
        try {
            ResultSet rs = WorkDB.stmt.executeQuery(sql); //объект принимает результатт запроса и выполняет его в БД
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
