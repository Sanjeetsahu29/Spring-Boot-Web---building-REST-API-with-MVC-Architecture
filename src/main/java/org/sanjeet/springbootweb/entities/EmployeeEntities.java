package org.sanjeet.springbootweb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="employees") // if this table annotation is missing, then spring automatically named is table as name of java class in small case
public class EmployeeEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
