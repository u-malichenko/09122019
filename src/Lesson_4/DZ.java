//package Lesson_4;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DZ {
//
//    public static void main(String[] args) {
//        TopObject topObject = new TopObject(); // создаем новый объект класса
//
//        topObject.addList (1, "null");
//        topObject.addList (2, "1");
//        topObject.addList(3, "2");
//        topObject.addList(4, "1");
//        topObject.addList(5, "null");
//        topObject.addList (6, "5");
//        topObject.addList (7, "null");
//
//
//
//    }
//}
//
//class TopObject {
//        int id;
//        String ParentId;
//        List<TopObject> children;
//
//
//    public void addList(int id, String parentId) {
//        this.id = id;
//        this.ParentId = parentId;
//        children.add(id,this);
//    }
//
//}
////Если у объекта ParentId == null значит нет родительского элемента
////нужно пройти все элементы и сложить их иерархию.