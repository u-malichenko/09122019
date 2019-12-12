package Lesson_1.Marathon.Team;

public class Human extends Mammals {
    public Human(String name) {
        super("Человек", name, 1000, 3, 100);
    }

    public Human(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        super("Человек", name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}
