package Cloud2024.ReactiveUsersMicroservice.Domain;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String LastName;
    private String email;
    private String password;
    private String birthdate;
    private String recruitDate;
    private ArrayList<String> roles;
    @DBRef
    private String deptId;

    public UserEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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

     public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public int calculateAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthdate = LocalDate.parse(this.birthdate, formatter);
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - birthdate.getYear();
        if (birthdate.getMonthValue() > currentDate.getMonthValue() ||
                (birthdate.getMonthValue() == currentDate.getMonthValue() && birthdate.getDayOfMonth() > currentDate.getDayOfMonth())) {
            age--;
        }
        return age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", recruitDate='" + recruitDate + '\'' +
                ", roles=" + roles +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}
