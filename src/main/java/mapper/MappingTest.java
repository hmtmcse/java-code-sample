package mapper;

import mapper.exp.GenericCopy;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;

public class MappingTest {

    private Student student;

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(20);
        student.setEmail("email@email.local");
        student.setName("Student Name");

//        Student otherStudent = new Student();
//        otherStudent.setAge(10);
//        otherStudent.setEmail("other@email.local");
//        otherStudent.setName("Other Name");
//        student.addOthers(otherStudent);

//        Student friend = new Student();
//        friend.setAge(12);
//        friend.setEmail("friend@email.local");
//        friend.setName("Friend Name");
//        student.friend = friend;

//        ModelMapper modelMapper = new ModelMapper();
//        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
//        studentDTO.friend = new Student();
//
//        System.out.println(studentDTO.getAge());
//        System.out.println(studentDTO.getEmail());
//        System.out.println(studentDTO.getName());

//        StudentDTO myDto = new StudentDTO();
//        GenericCopy.copyFields(student, myDto);
//
//        System.out.println(myDto.getAge());
//        System.out.println(myDto.getEmail());
//        System.out.println(myDto.getName());

        fieldList(StudentDTO.class);

    }


    public static void fieldList(Class<?> destinationType){
        Field[] allFields = destinationType.getDeclaredFields();
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                System.out.println(field.getName());
            }else if (isPrimitivish(field.getType()) || field.getType().isEnum()){
                System.out.println(field.getName());
            }else{
                System.out.println("----------------------");
                System.out.println(field.getName());
                fieldList(field.getType());
                System.out.println("======================");
            }
        }
    }



    private static boolean isPrimitivish(Class c) {
        return c.isPrimitive() || c == String.class || c == Boolean.class
                || c == Byte.class || c == Short.class || c == Character.class
                || c == Integer.class || c == Float.class || c == Double.class
                || c == List.class || c == Date.class || c == Long.class;
    }


}
