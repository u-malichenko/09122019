package Lesson_1.Marathon;


import Lesson_1.Marathon.Course.*;
import Lesson_1.Marathon.Team.*;

public class Main {
    public static void main(String[] args) {
        Course c = new Course(new Cross(80), new Wall(2), new Water(15), new Cross(120), new Wall(3)); // Создаем полосу препятствий
        Team team1 = new Team("team1", new Dog("Бобик"),new Cat("Мурка", 150, 15, 0), new Cat("Барсик"), new Human("Боб"),new Dog("Пёса",150, 15, 20)); // Создаем команду
        c.doIt(team1); // Просим команду пройти полосу
        team1.showResults(); // Показываем результаты
    }
}