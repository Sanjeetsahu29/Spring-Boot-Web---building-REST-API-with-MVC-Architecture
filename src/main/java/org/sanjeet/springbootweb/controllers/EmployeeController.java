package org.sanjeet.springbootweb.controllers;

import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.sanjeet.springbootweb.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping("/*")
//    public String handleRandomURL(){
//        return "Incorrect URL, please go back and hit correct URL";
//    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Sanjeet Sahu", "sanjeetsahus29@gmail.com", 28, LocalDate.of(2026, 2, 22), true);
    }

    @GetMapping("/")
    public String getMessageWithQueryParameter(@RequestParam(required = false) Integer age,
                                              @RequestParam(required = false) String sortBy){
        return "Hard coded Query parameter are age: "+age+" and SortBy filter method: "+sortBy;
    }
    // Since this is the controller method of type post, so you can't be able to hit this
    // api request from the browser url. We need a client that can mimic our frontend client
    // Using POSTMAN we can make api request of different HTTP methods
    @PostMapping("/")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployeeInfo){
        inputEmployeeInfo.setId(100L);
        return inputEmployeeInfo;
    }

    @PutMapping("/")
    public String updateEmployee(){
        return "Hello from the Put controller method";
    }

    @DeleteMapping("/")
    public String deleteEmployee(){
        return "Hello from the Delete controller method";
    }
}
