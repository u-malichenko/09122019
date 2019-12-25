package Lesson_3.PhoneBook;

import java.util.*;

/**
 * Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * <p>
 * В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * <p>
 * С помощью метода get() искать номер телефона по фамилии.
 * <p>
 * Следует учесть, что под одной фамилией может быть несколько телефонов, тогда при запросе такой фамилии должны выводиться все телефоны.
 * <p>
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
 * делать взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().
 */


class MainPhoneBook {
    public static void main(String[] args) {
        PhoneBook newPhoneBook = new PhoneBook(); // создаем новый объект класса

        for (int i = 0; i < 15; i++) {
            newPhoneBook.add("Ivanov " + i, "+7921445010" + i); //инициируем заполнеие нового объекта класса новыми записями
        }

        newPhoneBook.add("Ivanov " + 1, "+7921445010" + 9); //добавляем вторую запись

        for (int i = 0; i < 17; i++) {
            newPhoneBook.get("Ivanov " + i); //выводим информацию
        }
    }
}

/**
 * Класс Телефонная книжка
 * реализует хранинее
 * добавление добавление add
 * поиск g фамилии - метод get
 */
class PhoneBook {
    private Map<String, HashSet<String>> phoneBookHM = new HashMap<>();
    private HashSet<String> phoneHS;
    HashSet<String> notFound = new HashSet<>(); //для реализации дефолтного значения если в справочнике нет такой фамилии

    /**
     * Метод для добавления элементов в спраочник
     *
     * @param surname фамилия
     * @param phone   телефон
     */
    public void add(String surname, String phone) {
        if (phoneBookHM.containsKey(surname)) { //проверяем есть ли такой ключ в коллекции
            phoneHS = phoneBookHM.get(surname); // если есть то копируем его коллекцию с телефонаи во временную коллецию phoneHS (для добавления нового номера)
        } else {
            phoneHS = new HashSet<>(); //создаем новую коллекцию, если такого s ключа еще не было
        }
        phoneHS.add(phone); //добавляем телефон в коллекцию этого ключа
        phoneBookHM.put(surname, phoneHS); //добавляем эту колекцию с уникальными (set) ключами в коллекцию нашего справочника

    }

    /**
     * vМетод поиска по фамилии, печать в консоль
     *
     * @param surname - фамилия = ключ Map
     */
    public void get(String surname) {
        notFound.add("нет такого номера"); //для реализации дефолтного значения если в справочнике нет такой фамилии
        System.out.println(surname + " " + phoneBookHM.getOrDefault(surname, notFound));
    }

}