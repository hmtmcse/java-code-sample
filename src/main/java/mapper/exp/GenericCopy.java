package mapper.exp;

import java.lang.reflect.Field;

public class GenericCopy {

    /**
     * Deep-copies the values from one object to the other
     *
     */
    public static void main(String[] args) {


        A a1 = new A(1, 2, new B("string 1", 10));


        A a2 = new A(3, 4, new B("string 2", 20));


        System.out.println("a1 is :" + a1);

        System.out.println("a2 is :" + a2);


        copyFields(a1, new A());



        System.out.println("After copying...");


        System.out.println("a1 is :" + a1);

        System.out.println("a2 is :" + a2);

    }

    public static <T> void copyFields(T from, T to) {
        for (Field f : from.getClass().getFields()) {
            try {
                if (isPrimitivish(f.getType())) {
                    f.set(to, f.get(from));
                } else {
                    copyFields(f.get(from), f.get(to));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isPrimitivish(Class c) {
        return c.isPrimitive() || c == String.class || c == Boolean.class
                || c == Byte.class || c == Short.class || c == Character.class
                || c == Integer.class || c == Float.class || c == Double.class
                || c == Long.class;
    }
}

class A {

    public Integer x;
    public Integer y;
    public B bObj = new B();

    public A() {
    }

    public A(int x, int y, B b) {
        this.x = x;
        this.y = y;
        this.bObj = b;
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.y + "," + this.bObj.toString() + "]";
    }
}

class B {

    public String str;
    public Integer z;

    public B() {
    }

    public B(String str, int z) {


        this.str = str;

        this.z = z;

    }

    @Override
    public String toString() {

        return "[" + this.str + "," + this.z + "]";
    }
}
