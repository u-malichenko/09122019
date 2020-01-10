package Lesson_4;

import java.util.ArrayList;
import java.util.List;

class MainTopObject1 {
    static List<TopObject1> topObjects = new ArrayList<>();

    public static void main(String[] args) {

        topObjects.add(new TopObject1(1, null));
        topObjects.add(new TopObject1(2, "1"));
        topObjects.add(new TopObject1(3, "2"));
        topObjects.add(new TopObject1(4, "1"));
        topObjects.add(new TopObject1(5, null));
        topObjects.add(new TopObject1(6, "5"));
        topObjects.add(new TopObject1(7, null));

        for (TopObject1 obj : topObjects) {
            if (obj.hasParent()){
                setChild(obj);
            }
        }

        for (TopObject1 obj : topObjects) {
            System.out.println("Parent: " + obj.getId() + " Children: ");
            obj.showChildren();
        }
    }

    private static void setChild(TopObject1 topObject) {
        int parentId;
        try{
            parentId = Integer.parseInt(topObject.getParentId());
        } catch (NumberFormatException e){
            System.out.println("Unknown parentId");
            return;
        }

        for (TopObject1 object : topObjects) {
            if(object.getId() == parentId){
                object.setChild(topObject);
            }
        }

    }

}

class TopObject1 {
    private int id;
    private String parentId;
    private ArrayList<TopObject1> children = new ArrayList<>();

    TopObject1(int id, String parentId){
        this.id = id;
        this.parentId = parentId;
    }

    public ArrayList<TopObject1> getChildren() {
        return children;
    }

    public int getId() {return id;}

    public String getParentId() {return parentId;}

    public boolean hasParent(){
        if(this.parentId != null){ //перент айди не нулевой! = тру
            return true;
        }
        return false;
    }

    public void showChildren() {
        if(children.isEmpty()){
            System.out.println("No child objects");
            return;
        }
        for (TopObject1 child : children) {
            System.out.println(child.getId());
        }
    }

    public void setChild(TopObject1 child) {

        this.children.add(child);
    }
}
