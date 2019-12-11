package Lesson_1.Marathon.Team;

public interface Competitor {
    void run(int dist);
    void swim(int dist);
    void jump(int height);
    boolean isOnDistance();
    void info();
    void putMeInArray(Competitor[] team, int i);
}
