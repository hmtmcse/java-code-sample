package mapper.entity;

public class Student extends BaseEntity {

    public String firstName;
    public String lastName;
    public String identity;
    public String mobile;
    public Address address;
    public Gender gender;
    private Boolean isActive;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
