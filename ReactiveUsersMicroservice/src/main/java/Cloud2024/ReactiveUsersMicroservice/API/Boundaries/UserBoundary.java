package Cloud2024.ReactiveUsersMicroservice.API.Boundaries;

import java.util.ArrayList;

public class UserBoundary {
    private String name;
    private String email;
    private String password;
    private String birthdate;
    private String recruitDate;
    private ArrayList<String> roles;

    public UserBoundary() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", recruitDate='" + recruitDate + '\'' +
                ", roles=" + roles +
                '}';
    }
}
