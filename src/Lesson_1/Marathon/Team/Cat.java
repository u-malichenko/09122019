package Lesson_1.Marathon.Team;

public class Cat extends Mammals {
    public Cat(String name) {
        super("Кот", name, 200, 20, 0);
    }
    public Cat(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        super("Кот", name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}