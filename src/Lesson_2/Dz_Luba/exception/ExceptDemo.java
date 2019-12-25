package Lesson_2.Dz_Luba.exception;

public class ExceptDemo {

    public static void arrExp(String [][] arr) {
        System.out.println("Проверка размера массива:");

        try {
            if (arr.length != 4) throw new MyArraySizeException("Количество строк отличается от 4, равно: " + arr.length);
        }
        catch (MyArraySizeException e){
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < arr.length; i++) {
            try {
                if (arr[i].length != 4)
                    throw new MyArraySizeException("Количество столбцов в строке " + i + " отличается от 4, равно: " + arr[i].length);
            }
            catch (MyArraySizeException e){
                System.out.println(e.getMessage());
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                }
                catch (NumberFormatException nfe){
                    MyArrayDataException exp = new MyArrayDataException(i, j);
                    System.out.println(exp.toString());
                }
            }
        }
        System.out.println("Сумма членов массива: " + sum);
        System.out.println();
    }

    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        String[][] arr = {
                {"AS", "100", "5", "8"},
                {"FG", "4", "13", "9"},
                {"98", "34", "12", "10"},
                {"0", "3", "3", "11"}
        };

        String[][] arr1 = {
                {"LF", "5", "8"},
                {"4", "13", "9"},
                {"98", "34", "ДЛ", "5", "10"}
        };

        ExceptDemo.arrExp(arr);
        ExceptDemo.arrExp(arr1);
    }
}
