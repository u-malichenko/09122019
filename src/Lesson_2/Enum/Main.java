package Lesson_2.Enum;

import java.time.*;

public class Main {
    public static void main(String[] args) {
        testEnum (WeekEnum.SUNDAY);
        testEnumRealTime();
    }
    public static void testEnum (WeekEnum day) {
        if (day.getTime() <= 16){
            System.out.println("УРА! сегодня "+day.name()+" А значит сейчас выходной день!");
        } else System.out.println(day.getTime()+ " Часов осталось отработать до окнца этой недели");
    }

    public static void testEnumRealTime () {
        System.out.println("А реально меньше "+WeekEnum.valueOf(LocalDateTime.now().getDayOfWeek().name()).getTime()+ " часов осталось отработать до конца этой недели");
    }
}