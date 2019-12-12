package Lesson_1.Marathon.Team;

public class Dog extends Mammals {
    public Dog(String name) {
        super("Пес", name, 500, 5, 10);
    }

    public Dog(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        super("Пес", name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}