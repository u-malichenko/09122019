package Lesson_2.Exception;

public class MyArrayDataException extends MyException {
    int row, column;
    String val;

    public MyArrayDataException(String val, int row, int column) {
        super("Ячейка c индексом(" + (row) + " строка, " + (column) + " ячейка) содержит: ' " + val + " ' И не может быть преобразована в число!");
        this.row = row;
        this.column = column;
        this.val = val;
    }
//    public String toString(){
//        return "Ошибка преобразования в ячейке: " + m + "х" + n;
//    }
}

