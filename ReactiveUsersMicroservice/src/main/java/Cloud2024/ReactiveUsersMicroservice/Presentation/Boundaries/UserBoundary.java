package Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries;


import java.io.Serializable;
import java.util.ArrayList;

public class UserBoundary implements Serializable {
    private NameBoundary name;
    private String email;
    private String password;
    private String birthdate;
    private String recruitDate;
    private ArrayList<String> roles;
    private DepartmentBoundary department;

    public UserBoundary() {
    }

    public NameBoundary getName() {
        return name;
    }

    public void setName(NameBoundary name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRecruitDate() {
        return recruitDate;
    }

    public void setRecruitDate(String recruitDate) {
        this.recruitDate = recruitDate;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public DepartmentBoundary getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentBoundary department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserBoundary{" +
                "name=" + name +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", recruitDate='" + recruitDate + '\'' +
                ", roles=" + roles +
                ", department=" + department +
                '}';
    }
}
