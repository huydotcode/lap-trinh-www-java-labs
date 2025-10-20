package iuh.fit.se.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Table
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "First Name không được phép rỗng")
    private String firstName;

    @NotEmpty(message = "Last Name không được phép rỗng")
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Email không được phép rỗng")
    private String email;

    @NotEmpty(message = "Phone không được phép rỗng")
    private String phone;
}
