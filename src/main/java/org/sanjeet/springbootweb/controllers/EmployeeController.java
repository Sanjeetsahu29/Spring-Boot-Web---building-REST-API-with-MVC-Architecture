package org.sanjeet.springbootweb.controllers;

import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {
    @GetMapping("/employees/*")
    public String handleRandomURL(){
        return "Incorrect URL, please go back and hit correct URL";
    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Sanjeet Sahu", "sanjeetsahus29@gmail.com", 28, LocalDate.of(2026, 2, 22), true);
    }
}
