package Lesson_2.Dz_Luba.workhouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestEnum {
    public static void main(String[] args) throws IOException, MyEnumException {
        //Считывание дня недели c консоли
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Проверка введнных данных и, если все ОК! вывод кол-ва рабочих часов оставшихся  до конца недели
        WorkingHours workingHours = new WorkingHours();
        System.out.println(workingHours.getWorkingHours(reader.readLine()));
        System.out.println(workingHours.getWorkingHours(DayOfWeek.FRIDAY));
        System.out.println(workingHours.getWorkingHours(DayOfWeek.MONDAY));
        System.out.println(workingHours.getWorkingHours(DayOfWeek.SUNDAY));

        /*
        for (DayOfWeek dayOfWeek : DayOfWeek.values()){
            System.out.println(dayOfWeek + " " + dayOfWeek.getRus());
        }
         */
    }
}
