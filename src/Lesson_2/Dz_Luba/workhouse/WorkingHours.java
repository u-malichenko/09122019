package Lesson_2.Dz_Luba.workhouse;

public class WorkingHours {
    private DayOfWeek dayOfWeek;
    private int day;

    public void setDay(String d) throws MyEnumException {
        this.day = inputValidation(d);
    }

    public int inputValidation(String s) throws MyEnumException {
        String str = s.toUpperCase();
        int num = 0;

        //Проверка на соответствие дням недели и корректности ввода
        try {
            dayOfWeek = DayOfWeek.valueOf(str);
            num = dayOfWeek.getRemains();
            return num;
        }
        catch (IllegalArgumentException e){
            if (s.equals("")) str = "нулевая строка.";
            MyEnumException exp = new MyEnumException("Ошибка ввода данных: " + str);
            System.out.println(exp.getMessage());
            System.out.println("Установка по умолчанию: понедельник.");
            dayOfWeek = DayOfWeek.MONDAY;
            return num = DayOfWeek.MONDAY.getRemains();
        }
    }

    public String getWorkingHours(String d) throws MyEnumException {
        setDay(d);
        return calcHours();
    }

    public String getWorkingHours(DayOfWeek d){
        dayOfWeek = d;
        day = d.getRemains();
        return calcHours();
    }

    private String calcHours(){
        String hours = dayOfWeek.name() + ": ";

        switch (dayOfWeek){
            case MONDAY:
            case THURSDAY:
            case WEDNESDAY:
            case TUESDAY:
            case FRIDAY:
                hours += (day * 8) + " часов до выходных";
                break;
            case SATURDAY:
            case SUNDAY:
                hours += "Пора отдыхать, уже давно выходные!";
                break;
        }
        return hours;
    }
}
