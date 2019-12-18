package Lesson_2.Dz_Luba.exception;

public class MyArrayDataException extends Exception{
    int m;
    int n;

    public MyArrayDataException(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public String toString(){
        return "Ошибка преобразования в ячейке: " + m + "х" + n;
    }
}
