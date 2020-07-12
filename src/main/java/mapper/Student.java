package mapper;

import sun.util.calendar.BaseCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student {

    public String name;
    public Integer age;
    public String email;
    public Date dob;
    public Gender gender;
    public String password;
    public Student friend;
    public List<Student> others = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

//    public List<Student> getOthers() {
//        return others;
//    }
//
//    public void setOthers(List<Student> others) {
//        this.others = others;
//    }
//
//    public void addOthers(Student student) {
//        this.others.add(student);
//    }


}
