package Lesson_2.Exception;

public class Main {
    public static void main(String[] args) {
        String[][] array = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        try {
            MyArray(array);
        } catch (MyArraySizeException eSize) {
            eSize.getMessage();
            eSize.printStackTrace();
        } catch (MyArrayDataException eData) {
            eData.getMessage();
            eData.printStackTrace();
        }
    }

    public static void MyArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (array.length != 4) {
            throw new MyArraySizeException("Количество строк в массиве должно = 4!");
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i].length != 4) {
                        throw new MyArraySizeException("Количество значений в " + (i + 1) + " строке массива должно = 4!");
                    }
                    try {
                        sum += Integer.parseInt(array[i][j]);
                    }catch (NumberFormatException e1){
                        throw new MyArrayDataException("Ячейка c адресом(" + (++i) + " строка, " + (++j) + " ячейка) содержит: ' " + array[i][j]+" ' И не может быть преобразована в число!");
                    }
                }
            }
        }
        System.out.println(sum);
    }
}


/**
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 *
 * 2. Далее метод должен пройтись по всем элементам массива,
 * преобразовать в int, и просуммировать.
 *
 * Если в каком-то элементе массива преобразование не удалось
 * (например, в ячейке лежит символ или текст вместо числа),
 * должно быть брошено исключение MyArrayDataException,
 * с детализацией в какой именно ячейке лежат неверные данные.
 *
 * 3. В методе main() вызвать полученный метод,
 * обработать возможные исключения MySizeArrayException и MyArrayDataException,
 * и вывести результат расчета.
 */
