package Lesson_2.Dz_Luba.workhouse;

public enum DayOfWeek {
    MONDAY("ПОНЕДЕЛЬНИК", 5), TUESDAY("ВТОРНИК", 4), WEDNESDAY("СРЕДА", 3),
    THURSDAY("ЧЕТВЕРГ", 2), FRIDAY("ПЯТНИЦА", 1),
    SATURDAY("СУББОТА", 0), SUNDAY("ВОСКРЕСЕНЬЕ", 0);

    private String rus;
    private int remains;

    public String getRus() {
        return rus;
    }

    public int getRemains() {
        return remains;
    }

    DayOfWeek(String rus, int remains) {
        this.rus = rus;
        this.remains = remains;
    }
}
