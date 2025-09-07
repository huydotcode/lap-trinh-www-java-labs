package iuh.fit.se.bai02;

import java.time.LocalDate;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;   // sẽ mã hoá khi lưu DB
    private LocalDate birthday;
    private String gender;     // "Male" hoặc "Female"

    public User() {}

    public User(String firstName, String lastName, String email, String password,
                LocalDate birthday, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
