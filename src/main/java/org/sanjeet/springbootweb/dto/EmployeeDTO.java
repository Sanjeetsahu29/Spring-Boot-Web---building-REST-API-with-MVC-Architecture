package org.sanjeet.springbootweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Required field in the employee: name")
    private String firstName;
    @NotBlank(message = "lastName field can neither be null nor empty")
    private String lastName;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;

}
