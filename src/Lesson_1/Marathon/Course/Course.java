package Lesson_1.Marathon.Course;

import Lesson_1.Marathon.Team.*;

public class Course {
    Obstacle[] arrCourse;

    public Course(int crossLength, int wallHeight, int waterLength, int cross2Length) {
        Obstacle cross1 = new Cross(crossLength);
        Obstacle wall1 = new Wall(wallHeight);
        Obstacle water1 = new Water(waterLength);
        Obstacle cross2 = new Cross(cross2Length);

        this.arrCourse = new Obstacle[4]; //создали массив препятствий через абстрактный класс, все препятствия наследники абстрактного класса Obstacle
        cross1.putMeInArray(this.arrCourse, 0); //заносим в массив препятствия различные подклассы абстрактного класса Obstacle
        wall1.putMeInArray(this.arrCourse, 1);
        water1.putMeInArray(this.arrCourse, 2);
        cross2.putMeInArray(this.arrCourse, 3);
    }

    public void doIt(Team team) {

        for (Competitor c : team.getArrTeam()) { //каждого участника отправляем на каждое препятствие:
            for (Obstacle o : this.arrCourse) {
                o.doIt(c);
                if (!c.isOnDistance())
                    break; // если он не преодолел дистануцию то выходим из набора препятствий и берем следующего участника
            }
        }

    }

}

