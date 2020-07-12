package mapper;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO {

    public String name;
    public Integer age;
    public String email;
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

}
