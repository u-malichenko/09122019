package Lesson_2.Enum;

public enum WeekEnum {
    MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY;

    public int getTime() {
        return 40 - (this.ordinal()*8);
    }
}
