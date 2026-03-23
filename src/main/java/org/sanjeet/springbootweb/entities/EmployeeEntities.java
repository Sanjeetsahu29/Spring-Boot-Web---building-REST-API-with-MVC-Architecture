package org.sanjeet.springbootweb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employees") // if this table annotation is missing, then spring automatically named is table as name of java class in small case
public class EmployeeEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role;
    private Integer salary;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
