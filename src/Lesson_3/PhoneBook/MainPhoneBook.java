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
        PhoneBook newPhoneBook = new PhoneBook();

        for (int i = 0; i < 15; i++) {
            newPhoneBook.add("Ivanov " + (char) (i + 65), "+7921445010" + i);
        }

        newPhoneBook.add("Ivanov " + (char) 66, "+7921445010" + 9);

        newPhoneBook.get("Ivanov " + (char) 65);
        newPhoneBook.get("Ivanov " + (char) 66);
        newPhoneBook.get("Ivanov " + (char) 90);

    }
}

/**
 * Класс Телефонная книжка
 * реализует хранинее
 * добавление добавление add
 * поиск g фамилии - метод get
 */
class PhoneBook {
    private Map<String, List<String>> phoneBook = new HashMap<>();
    private List<String> phoneList;
    String[] notFound = {"нет в справочнике"}; //для реализации примера из видеоурока

    /**
     * Метод для добавления элементов в спраочник
     *
     * @param surname фамилия
     * @param phone   телефон
     */
    public void add(String surname, String phone) {
        if (phoneBook.containsKey(surname)) {
            phoneList = phoneBook.get(surname);
        } else {
            phoneList = new ArrayList<>();
        }
        phoneList.add(phone);
        phoneBook.put(surname, phoneList);

    }

    /**
     * vМетод поиска по фамилии, печать в консоль
     *
     * @param surname - фамилия = ключ Map
     */
    public void get(String surname) {
        System.out.println(surname + " " + phoneBook.getOrDefault(surname, Arrays.asList(notFound)));
    }

}