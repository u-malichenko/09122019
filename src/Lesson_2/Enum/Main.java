package Lesson_2.Enum;

import java.time.*;

public class Main {
    public static void main(String[] args) {
        testEnum(WeekEnum.SUNDAY);
        testEnumRealTime();
    }


    public static void testEnum(WeekEnum day) {
        int LeftHours = day.getTime();
        if (LeftHours == 0) {
            System.out.println("УРА! сегодня " + day.name() + " А значит сейчас выходной день!");
        } else {
            System.out.println(LeftHours + " Часов осталось отработать до окнца этой недели");
        }
    }

    public static void testEnumRealTime() {

        String CurrentDayOfWeek = LocalDateTime.now().getDayOfWeek().name();
        System.out.println("А реально меньше " + WeekEnum.valueOf(CurrentDayOfWeek).getTime() + " часов осталось отработать до конца этой недели");
    }
}