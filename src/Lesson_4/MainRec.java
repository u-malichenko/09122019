package Lesson_4;

import java.util.*;

public class MainRec {
    static List<TopObject> topObjects = new ArrayList<>();

    public static void main(String[] args) {
        new TopObject(1, null);
        new TopObject(2, 1);
        new TopObject(3, 2);
        new TopObject(4, 1);
        new TopObject(5, null);
        new TopObject(6, 5);
        new TopObject(7, null);
        new TopObject(8, 3);
        System.out.println(" See topObjects debug");
    }
}

class TopObject {
    int id;
    Integer parentId;
    List<TopObject> children;

    public TopObject(int id, Integer parentId) {
        this.id = id;
        this.parentId = parentId;

        if (this.parentId == null) {
            MainRec.topObjects.add(this);
            this.children = new ArrayList<>();
        } else {
            processChilds(MainRec.topObjects);
        }
    }

    void processChilds(List<TopObject> child) {
        for(TopObject i: child) {
            if (i.id == this.parentId) {
                this.children = new ArrayList<>();
                i.children.add(this);
                return ;
            } else if (i.children != null)
                processChilds(i.children);
        }
    }
}

/**
 Имеется класс

 TopObject {
 int id;
 string ParentId;
 List<TopObject> children;
 }


 Если у объекта ParentId == null значит нет родительского элемента
 нужно пройти все элементы и сложить их иерархию.

 Список объектов

 {
 id = 1;
 ParentId = null;
 }

 {
 id = 2;
 ParentId = 1;
 }

 {
 id = 3;
 ParentId = 2;
 }

 {
 id = 4;
 ParentId = 1;
 }

 {
 id = 5;
 ParentId = null;
 }

 {
 id = 6;
 ParentId = 5;
 }

 {
 id = 7;
 ParentId = null;
 }
 **/