package org.sanjeet.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private Long id;

    @NotBlank(message = " name field can neither be null nor empty")
    @Size(min = 3, max = 50, message = "Number of characters in name should be in the range [3,50]")
    private String name;

    @Email(message = "Email should be a valid email")
    private String email;

    @Max(value = 80, message = "Age of employee cannot be greater than 80")
    @Min(value = 18, message = "Age of employee must be 18+")
    private Integer age;

    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of the employee can be either admin or user")
    private String role;

    @NotNull(message = "Salary of employee should not be null")
    @Positive(message = "Salary of employee should be positive")
    private Integer salary;

    @PastOrPresent(message = "DateOfJoining field in employee cannot be in the future")
    private LocalDate dateOfJoining;

    @Digits(integer = 1, fraction = 2, message = "Rating of the employee is in format X.YZ")
    @Min(value = 1, message = "Min rating of the employee can be 1")
    @Max(value = 5, message = "Max rating of the employee can be 5")
    private Double rating;

    @DecimalMin(value = "1.0", message = "minimum safety rating that a employee can earn is 1.0")
    @DecimalMax(value = "9.9", message = "maximum safety rating that a employee can earn is 9.9")

    private Double safetyRating;

    @JsonProperty("isActive")
    private Boolean isActive;

}
