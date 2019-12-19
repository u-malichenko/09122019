package Lesson_3.Cillection;

import java.util.*;

/**
 * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать сколько раз встречается каждое слово.
 */
public class Collection {

    public static void main(String[] args) {
        final int sizeArray = 20; //задаем длиннуу массива
        String[] originalArray = new String[sizeArray]; //исходный массив

        getArray(originalArray); //генерация массива слов
        System.out.println("Исходный массив: " + Arrays.toString(originalArray));

        ArrayList<String> originalArrayList = new ArrayList<>(Arrays.asList(originalArray)); //сконевертировал масив в лист

        HashSet<String> uniqueCollection = new HashSet<>(originalArrayList); //массив с уникальными значениями из исходного
        System.out.println("Уникальная колекция: " + uniqueCollection);

        HashMap<String, Integer> mapCollection = countArray(originalArrayList); //запускаем метод для генерирования Map

        printMap(mapCollection); // печать количества циклом

    }

    /**
     * Метод для печати количества повторений каждого слова из originalArray
     *
     * @param mapCollection - колекция в которой уже собраны ключи(уникальные строки) и занчения(количество повторений этих строк)
     */
    private static void printMap(HashMap<String, Integer> mapCollection) {
        for (HashMap.Entry<String, Integer> pair : mapCollection.entrySet()) { //entrySet() возвращает список всех пар в нашей HashMap Интерфейс Map.Entry обозначает как раз пару “ключ-значение” внутри словаря
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }

    /**
     * метод для подсчета сколько раз встречается каждое слово.
     *
     * @param arrayList - исходный список сгенерированных слов
     * @return - коллекция где ключи = словам из originalArray, а значения это количество повторений ключей
     */
    private static HashMap<String, Integer> countArray(ArrayList<String> arrayList) {
        HashMap<String, Integer> result = new HashMap<>(); //временная коллекция, нужна для генерации основной

        for (String s : arrayList) {
            if (!result.containsKey(s)) { //методы containsKey()(проверяет наличие какого-то ключа) и containsValue() (проверяет наличие значения).
                result.put(s, 1);
            } else if (result.containsKey(s)) {
                result.put(s, result.get(s) + 1);
            }
        }
        return result;
    }

    /**
     * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся)
     *
     * @param originalArray - новый массив String'ов
     */
    public static void getArray(String[] originalArray) {
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = getLetters(); //запускаем метод для генерирования случайных букв в слова массива
        }
    }

    /**
     * метод для генерирования случайных букв из symbolsArray в слова для originalArray
     *
     * @return - готовое слово для originalArray
     */
    public static String getLetters() {
        int sizeStrings = 3; //количество букв в генерируемых словах
        String[] symbolsArray = {"a", "b"}; //строка символов для создания слов
        StringBuilder letters = new StringBuilder(); // строка с буквами
        Random random = new Random();

        for (int i = 0; i < sizeStrings; i++) {
            letters.append(symbolsArray[random.nextInt(symbolsArray.length)]);
        }
        return letters.toString();
    }
}
