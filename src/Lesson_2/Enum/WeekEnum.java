package Lesson_2.Enum;

public enum WeekEnum {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    final int HoursOfWeek = 40;
    final int HoursOfDay = 8;

    public int getTime() {
        int LeftHours;
        LeftHours = HoursOfWeek - (this.ordinal() * HoursOfDay);
        if (LeftHours < HoursOfDay){
            LeftHours = 0;
        }
        return LeftHours;
    }
}
