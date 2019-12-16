package Lesson_2.Exception;

public class Main {
    public static void main(String[] args) {
        String[][] array = {{"1", "2", "3", "4"},
                            {"5", "6", "7", "8"},
                            {"9", "10", "11", "12"},
                            {"13", "14", "15", "16"}};
        try {
            sumArray(array);
        } catch (MyException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Метод суммирования значений массива
     *
     * @param array
     * @throws MyArraySizeException
     * @throws MyArrayDataException
     */
    public static void sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        trySize(array);
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
    public static void trySize(String[][] array) throws MyArraySizeException {
        final int sizeArray = 4;
        if (array.length != sizeArray) {
            throw new MyArraySizeException("Количество строк в массиве должно = 4!");
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i].length != sizeArray) {
                        throw new MyArraySizeException("Проверьте количество значений в " + (i + 1) + " строке массива, должно = 4");
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
                    throw new MyArrayDataException("Ячейка c адресом(" + (++i) + " строка, " + (++j) + " ячейка) содержит: ' " + array[--i][--j] + " ' И не может быть преобразована в число!");
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
