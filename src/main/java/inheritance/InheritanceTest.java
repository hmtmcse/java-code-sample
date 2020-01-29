package inheritance;

public class InheritanceTest {

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.name = "BABA";
        parent.type = "Father";

        Child child = new Child();
        child.name = "SELE";
        child.type = "boy";
        child.age = 21.0;

//        Child xy = parent;   // Error
        Parent abc = child;
    }

}
