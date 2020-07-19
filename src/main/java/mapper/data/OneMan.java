package mapper.data;

import mapper.entity.Gender;

import java.util.Date;
import java.util.List;

public class OneMan {

    public String stringType;
    public Integer integerType;
    public Long longType;
    public Double doubleType;
    public Float floatType;     // Optional
    public Gender enumType;
    public Student objectType;
    public Byte byteType;
    public Date dateType;
//    public LocalDate localDateType;
//    public LocalDateTime localDateTimeType;
    public Boolean booleanType;
    public List<Student> objectListType;
    public List<String> stringListType;
//    public LinkedHashMap<String, Student> objectMapType;
//    public LinkedHashMap<Integer, String> stringMapType;

}
