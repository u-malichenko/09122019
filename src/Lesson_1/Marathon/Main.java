package Lesson_1.Marathon;

import Lesson_1.Marathon.Course.*;
import Lesson_1.Marathon.Team.*;

public class Main {
    public static void main(String[] args) {
        Course c = new Course(80, 2, 15, 120); // Создаем полосу препятствий
        Team team1 = new Team("team1"); // Создаем команду
        c.doIt(team1); // Просим команду пройти полосу
        team1.showResults(); // Показываем результаты
    }
}

