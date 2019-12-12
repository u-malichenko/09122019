package Lesson_1.Marathon.Course;

import Lesson_1.Marathon.Team.*;

public class Course {
    Obstacle[] obstacles;

    public Course(Obstacle...obstacle) {
        this.obstacles = obstacle;
    }

    public void doIt(Team team) {
        for (Competitor c : team.getArrTeam()) { //каждого участника отправляем на каждое препятствие:
            for (Obstacle o : this.obstacles) {
                o.doIt(c);
                if (!c.isOnDistance())
                    break; // если он не преодолел дистануцию то выходим из набора препятствий и берем следующего участника
            }
        }
    }
}

