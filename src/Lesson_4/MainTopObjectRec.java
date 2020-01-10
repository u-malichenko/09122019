package Lesson_4;

import java.util.ArrayList;
import java.util.List;

public class MainTopObjectRec {
    static List<TopObject1> topObjects = new ArrayList<>();
    static List<TopObject1> topObjectsResultList = new ArrayList<>();

    public static void main(String[] args) {
        topObjects.add(new TopObject1(1, null));
        topObjects.add(new TopObject1(2, "1"));
        topObjects.add(new TopObject1(3, "2"));
        topObjects.add(new TopObject1(4, "1"));
        topObjects.add(new TopObject1(5, null));
        topObjects.add(new TopObject1(6, "5"));
        topObjects.add(new TopObject1(7, null));

         topObjects.add(new TopObject1(8, "3"));

        for (TopObject1 obj : topObjects) {
            addTO(obj);
        }
       // fac(5);
    }

//    static void fac(int j) {
//        if (j == 0) {
//            return;
//        } {
//            System.out.println(j);
//            fac(j - 1);
//        }
//    }

    static void addTO(TopObject1 obj) { //передаем в метод обект из полного списка, тот который мы проверяем в цикле
        if (!obj.hasParent()) { // если обект имееет ноль в родителях (не тру =фолс)
            topObjectsResultList.add(obj); //добавляем в результирующую таблицу этот объект
        } else { //иначе если обект имеет не ноль в родителях (а предполагается индекс родителя) значит будем искать ему родиетля и туда добавлять!
            for (int i = 0; i < topObjectsResultList.size(); i++) { //делаем цикл длинной в размер результирующего массива, будем обходить новый массив в поисках родиетля

                addChild(obj, i); // запускаем мтеод добавления деток, передаем в его обект  из полного списка(кот мы проверяем) и индекс элемента для сравнения из результирующего списка

                if (topObjectsResultList.get(i).getId() == Integer.parseInt(obj.getParentId())) { //если результ.список с итым элементом имеет (запускаем геттер) айди = текушщий элемент полного списка.(геттер)иди родителя
                    topObjectsResultList.get(i).setChild(obj);//в результ.списке с итым элементом запускаем сеттер добавления текушего объекта
                    if (!obj.hasParent()) { //если обект имет нольь в родителях(сам верхний родитель)
                        addTO(topObjectsResultList.get(i)); //запускаем рекурсивно этот же мтоед передаем в его из резуль.списка итый обект
                    } else {
                        break; // иначе, если обект не верхний родитель прерываем и выходим из цикла по результирующему массиву
                    }
                }
            }
        }
    }

    static void addChild(TopObject1 obj, int i) {// мтеод добавления деток, получает обект  из полного списка(кот мы проверяем) и индекс элемента для сравнения из результирующего списка
        if (topObjectsResultList.get(i).getChildren().size() != 0) { //если в результирующем массиве итый элемент,запускаем (метод)провверяем размер списка деток если  он равен 0 ни чего не делаем(нужен для проверки детей поддетей)
           //тут должна быть рекурсия
            for (int j = 0; j < topObjectsResultList.get(i).getChildren().size(); j++) {//иначе - делаем цикл обхода в итом элементе результ.списка всех детей
                if (topObjectsResultList.get(i).getChildren().get(j).getId() == Integer.parseInt(obj.getParentId())) { //если в итом элементе в списке его деток, джитая детка (запускаем геттер)имеет индекс равный проверяемому обекту - индексу родителя
                    topObjectsResultList.get(i).getChildren().get(j).setChild(obj); //в итом элементе резулт.списка у джитой дочки запускам метод (сеттер) добавления нового текущего объекта
                    //тут можно прервать проверку завершить все циклы
                }
            } //конец цикла ждитых деток

        } // если список детей у итого элемента результирующей таблицы равен нулю выходим из добавления.Список пусст и проверять там нечего!
    }
}
