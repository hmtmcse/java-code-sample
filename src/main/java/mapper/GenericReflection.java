package mapper;

import mapper.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class GenericReflection {

    public static List<Student> list = new ArrayList<>();
    public static LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();
    public static HashMap<String, Boolean> hashMap = new HashMap<>();
    public String data;


    public static Boolean isMap(Class c) {
        return c == Map.class || c == LinkedHashMap.class
                || c == HashMap.class
                || c == SortedMap.class
                || c == TreeMap.class;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Field[] allFields = GenericReflection.class.getFields();
        for (Field field : allFields) {
            if (field.getType() == List.class) {
                ParameterizedType dataType = (ParameterizedType) field.getGenericType();
                Class<?> stringListClass = (Class<?>) dataType.getActualTypeArguments()[0];
                System.out.println(stringListClass);
            } else if (field.getType() == LinkedHashMap.class) {
                ParameterizedType mapData = (ParameterizedType) field.getGenericType();
                System.out.println("Test");
            }

            if (isMap(field.getType())){
                System.out.println("Map: " + field);
            }

        }
        Field stringListField = GenericReflection.class.getDeclaredField("data");
        System.out.println(stringListField);
    }

}
