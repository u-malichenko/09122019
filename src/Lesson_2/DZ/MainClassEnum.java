package Lesson_2.DZ;

public class MainClassEnum
{
    public static void main(String[] args)
    {
        DayOfWeek d = DayOfWeek.MONDAY;
        System.out.println(getWorkingHours(d));
    }

    public static String getWorkingHours(DayOfWeek d)
    {

        //    System.out.println(d.ordinal());
        int num = 5 - d.ordinal();

        return (num > 0) ? String.valueOf(num*8) + " hours" : "Weekend!!!";
    }
}

enum DayOfWeek
{MONDAY, TUESDAY, WENSDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}