package Lesson_1.Marathon.Team;

public class Team {
    String teamName;
    Competitor[] arrTeam;

    public Team(String teamName) {
        this.teamName = teamName;
        Mammals cat1 = new Cat("Барсик");
//всем участникам можно задавать различные характеристики, а можно и просто имя
        Mammals cat2 = new Cat("Мурка", 150, 15, 0);
        Mammals dog1 = new Dog("Бобик");
        Mammals human1 = new Human("Боб");
//создали массив команды через инрефейс, все участники наследники класса млекопитающие
        this.arrTeam = new Mammals[4];
//заносим в массив участников различных подклассов класса млекопитающие(куда имплементирован интерфейс участников)
        cat1.putMeInArray(this.arrTeam, 0);
        cat2.putMeInArray(this.arrTeam, 1);
        dog1.putMeInArray(this.arrTeam, 2);
        human1.putMeInArray(this.arrTeam, 3);
    }

    /**
     * метод вывода информации обо всех членах команды
     */
    public void info() {
        for (Competitor c : this.arrTeam) { // перебираем всех участников и выводиминформацию о каждом из них
            c.info();
        }
    }

    /**
     * метод для вывода информации о членах команды прошедших дистанцию
     */
    public void showResults() {
        for (Competitor c : this.arrTeam) { // перебираем всех участников и выводиминформацию о победителях
            if (c.isOnDistance())
                c.info();
        }
    }

    /**
     * Метод получения массива участников
     * @return
     */
    public Competitor[] getArrTeam() {
        return arrTeam;
    }
}



