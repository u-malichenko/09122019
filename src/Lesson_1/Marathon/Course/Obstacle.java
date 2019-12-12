package Lesson_1.Marathon.Course;

import Lesson_1.Marathon.Team.Competitor;

public abstract class Obstacle {
    public abstract void doIt(Competitor competitor); //могут попадать все объекты которые подписанны на интерфейс компетитор
}
//Стена - реализовывает метод дуит