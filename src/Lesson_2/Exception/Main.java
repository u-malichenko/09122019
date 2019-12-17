package Lesson_2.Exception;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int rawArray = 4;
        final int columnArray = 4;

        String[][] array = new String[rawArray][columnArray];

        generateArray(array, rawArray, columnArray);
        try {
            //array[1][3] = "q";
            sumArray(array, rawArray, columnArray);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void generateArray(String[][] array, int rawArray, int columnArray) {
        Random random = new Random();
        for (int i = 0; i < rawArray; i++) {
            for (int j = 0; j < columnArray; j++) {
                array[i][j] = String.valueOf(random.nextInt(50));
            }
        }
    }

    /**
     * Метод суммирования значений массива
     *
     * @param array
     * @throws MyArraySizeException
     * @throws MyArrayDataException
     */
    public static void sumArray(String[][] array, int rawArray, int columnArray) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        trySize(array, rawArray, columnArray);
        tryData(array);
        for (String[] strings : array) {
            for (String string : strings) {
                sum += Integer.parseInt(string);
            }
        }
        System.out.println("Сумма занчений массива равна " + sum);
    }

    /**
     * Метод проверки размера массива
     *
     * @param array
     * @throws MyArraySizeException
     */
    public static void trySize(String[][] array, int rawArray, int columnArray) throws MyArraySizeException {
        if (array.length != rawArray) {
            throw new MyArraySizeException("Количество строк в массиве должно = " + rawArray);
        } else {
            for (int i = 0; i < rawArray; i++) {
                for (int j = 0; j < columnArray; j++) {
                    if (array[i].length != columnArray) {
                        throw new MyArraySizeException("Проверьте количество значений в " + (i + 1) + " строке массива, должно = " + columnArray);
                    }
                }
            }
        }
    }

    /**
     * Метод проверки значений массива, что они числа
     *
     * @param array
     * @throws MyArrayDataException
     */
    public static void tryData(String[][] array) throws MyArrayDataException {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    Integer.parseInt(array[i][j]);
                } catch (NumberFormatException eData) {
                    throw new MyArrayDataException(array[i][j], i, j);//передаем значение и коорлинаты ошибочной ячейки в конструктор Exception
                }
            }
        }
    }
}

/**
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * <p>
 * 2. Далее метод должен пройтись по всем элементам массива,
 * преобразовать в int, и просуммировать.
 * <p>
 * Если в каком-то элементе массива преобразование не удалось
 * (например, в ячейке лежит символ или текст вместо числа),
 * должно быть брошено исключение MyArrayDataException,
 * с детализацией в какой именно ячейке лежат неверные данные.
 * <p>
 * 3. В методе main() вызвать полученный метод,
 * обработать возможные исключения MySizeArrayException и MyArrayDataException,
 * и вывести результат расчета.
 */
