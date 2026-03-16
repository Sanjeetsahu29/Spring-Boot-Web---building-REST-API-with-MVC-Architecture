package org.sanjeet.springbootweb.controllers;

import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/employees")
    public String getMessageWithQueryParameter(@RequestParam(required = false) Integer age,
                                              @RequestParam(required = false) String sortBy){
        return "Hard coded Query parameter are age: "+age+" and SortBy filter method: "+sortBy;
    }

    @PostMapping("/employees")
    public String createNewEmployee(){
        return "Hello from post controller method";
    }
}
