package Lesson_1.Marathon.Team;

public class Team {
    private String name;
    private Competitor[] competitors;
//private ArrayList<Competitor> competitors = new  ArrayList<Competitor>(); //создали массив команды через инрефейс, все участники наследники класса млекопитающие

    public Team(String teamName, Competitor... competitor) {
        this.name = teamName;
        this.competitors =competitor;
//        for (Competitor c: competitor) {
//            competitors.add(c);
//        }
    }

    /**
     * метод вывода информации обо всех членах команды
     */
    public void info() {
        for (Competitor c : this.competitors) { // перебираем всех участников и выводиминформацию о каждом из них
            c.info();
        }
    }

    /**
     * метод для вывода информации о членах команды прошедших дистанцию
     */
    public void showResults() {
        for (Competitor c : this.competitors) { // перебираем всех участников и выводиминформацию о победителях
            if (c.isOnDistance())
                c.info();
        }
    }

    /**
     * Метод получения массива участников
     * @return
     */
//    public ArrayList<Competitor> getArrTeam() {
    public Competitor[] getArrTeam() {
        return competitors;
    }
}



