package Lesson_3.Cillection;

import java.util.*;

/**
 * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать сколько раз встречается каждое слово.
 */
public class Collection {

    public static void main(String[] args) {
        int sizeArray = 20; //задаем длиннуу массива
        String[] originalArray = new String[sizeArray]; //исходный массив

        addToArray(originalArray); //генерация массива слов
        System.out.println("Исходный массив: " + Arrays.toString(originalArray));

        List<String> originalArrayList = new ArrayList<>(Arrays.asList(originalArray)); //сконевертировал масив в лист

        Set<String> uniqueCollection = new HashSet<>(originalArrayList); //массив с уникальными значениями из исходного
        System.out.println("Уникальная колекция: " + uniqueCollection);

        Map<String, Integer> mapCollection = countArray(originalArrayList); //запускаем метод для генерирования Map

        printMap(mapCollection); // печать количества циклом

    }

    /**
     * Метод для печати количества повторений каждого слова из originalArray
     *
     * @param map - колекция в которой уже собраны ключи(уникальные строки) и занчения(количество повторений этих строк)
     */
    private static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> pair : map.entrySet()) { //entrySet() возвращает список всех пар в нашей HashMap Интерфейс Map.Entry обозначает как раз пару “ключ-значение” внутри словаря
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }

    /**
     * метод для подсчета сколько раз встречается каждое слово.
     *
     * @param arrayList - исходный список сгенерированных слов
     * @return - коллекция где ключи = словам из originalArray, а значения это количество повторений ключей
     */
    private static Map<String, Integer> countArray(List<String> arrayList) {
        Map<String, Integer> result = new HashMap<>(); //временная коллекция, нужна для генерации основной

        for (String s : arrayList) {
            result.merge(s, 1, Integer::sum);
        }
        return result;
    }


    /**
     * метод заполнеиня массива набором слов (10-20 слов, должны встречаться повторяющиеся)
     *
     * @param originalArray - новый массив String'ов
     */
    public static void addToArray(String[] originalArray) {
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = addStrings(); //запускаем метод для генерирования случайных букв в слова массива
        }
    }

    /**
     * метод для генерирования случайных букв из symbolsArray в слова для originalArray
     *
     * @return - готовое слово для originalArray
     */
    public static String addStrings() {
        int sizeStrings = 3; //количество букв в генерируемых словах
        String[] originalLetters = {"a", "b"}; //строка символов для создания из них слов
        StringBuilder strings = new StringBuilder(); // строка с готовыми словами
        Random random = new Random();
        for (int i = 0; i < sizeStrings; i++) {
            int randomIndex = random.nextInt(originalLetters.length); //генерируем случайный индекс исходя из originalLetters
            String randomChar = originalLetters[randomIndex]; //выбираем нужную букву из массива originalLetters с индексом сгененрырованным ранее randomIndex
            strings.append(randomChar); //заполняем буквами слово
        }
        return strings.toString(); //возврашаем слово длинною sizeStrings=3
    }
}
