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

    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;

}
