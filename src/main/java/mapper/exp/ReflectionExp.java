package mapper.exp;

import com.hmtmcse.oc.reflection.ReflectionProcessor;
import mapper.entity.Address;
import mapper.entity.Gender;
import mapper.entity.Student;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionExp {

    public static <D> D xyz(Class<D> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        D object = c.getDeclaredConstructor().newInstance();

        Field field = c.getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(object, "First Name");

        field = c.getDeclaredField("lastName");
        field.setAccessible(true);
        field.set(object, "Last Name");

        field = c.getDeclaredField("identity");
        field.setAccessible(true);
        field.set(object, "123456");

        field = c.getDeclaredField("mobile");
        field.setAccessible(true);
        field.set(object, "01710000000");

        field = c.getDeclaredField("address");
        field.setAccessible(true);
        field.set(object, new Address());

        field = c.getDeclaredField("gender");
        field.setAccessible(true);
        field.set(object, Gender.MALE);

        field = c.getDeclaredField("isActive");
        field.setAccessible(true);
        field.set(object, true);


        return object;
    }


    public static Student init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<Student> c = Student.class;
        Object object = c.getDeclaredConstructor().newInstance();

        // creates object of the desired field by providing
        // the name of field as argument to the
        // getDeclaredField method
        Field field = c.getDeclaredField("firstName");

        // allows the object to access the field irrespective
        // of the access specifier used with the field
        field.setAccessible(true);

        // takes object and the new value to be assigned
        // to the field as arguments
        field.set(object, "First Name");

        field = c.getDeclaredField("lastName");
        field.setAccessible(true);
        field.set(object, "Last Name");

        field = c.getDeclaredField("identity");
        field.setAccessible(true);
        field.set(object, "123456");

        field = c.getDeclaredField("mobile");
        field.setAccessible(true);
        field.set(object, "01710000000");

        field = c.getDeclaredField("address");
        field.setAccessible(true);
        field.set(object, new Address());

        field = c.getDeclaredField("gender");
        field.setAccessible(true);
        field.set(object, Gender.MALE);

        field = c.getDeclaredField("isActive");
        field.setAccessible(true);
        field.set(object, true);


        return (Student) object;
    }


    public static <D> D copy(Object object, Class<D> c) throws IllegalAccessException{
        ReflectionProcessor reflectionProcessor = new ReflectionProcessor();
        D newInstance = reflectionProcessor.newInstance(c);
        Field newInstanceField;

        for (Field field : reflectionProcessor.getAllField(object.getClass())) {
            field.setAccessible(true);
            newInstanceField = reflectionProcessor.getFieldFromObject(newInstance, field.getName());
            if (newInstanceField != null) {
                newInstanceField.set(newInstance, field.get(object));
            }
            System.out.println(field.getName());
        }
        return newInstance;
    }


    public static void main(String[] args) {

        try{

            Student old = new Student();
            old.firstName = "Touhid";
            old.lastName = "Mia";
            old.identity = "123123123";
            old.mobile = "017777777777";
            old.gender = Gender.MALE;
            old.setActive(true);


            System.out.println("\n");
            Student student = copy(old, Student.class);
            System.out.println("\n");
            System.out.println(student.firstName);
            System.out.println(student.lastName);
            System.out.println(student.identity);
            System.out.println(student.mobile);
            System.out.println(student.gender);
            System.out.println(student.getActive());

        } catch ( IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
