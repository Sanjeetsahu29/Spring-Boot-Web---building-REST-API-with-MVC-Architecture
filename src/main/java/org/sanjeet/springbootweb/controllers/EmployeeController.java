package org.sanjeet.springbootweb.controllers;

import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.sanjeet.springbootweb.entities.EmployeeEntities;
import org.sanjeet.springbootweb.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }




//    @GetMapping("/*")
//    public String handleRandomURL(){
//        return "Incorrect URL, please go back and hit correct URL";
//    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/get-all")
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name="inputAge") Integer age,
                                                  @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployee();
    }
    // Since this is the controller method of type post, so you can't be able to hit this
    // api request from the browser url. We need a client that can mimic our frontend client
    // Using POSTMAN we can make api request of different HTTP methods
    @PostMapping("/create")
    public EmployeeEntities createNewEmployee(@RequestBody EmployeeEntities inputEmployeeInfo){
        return employeeService.createEmployee(inputEmployeeInfo);
    }

    @GetMapping()
    public String getMessageWithQueryParameter(@RequestParam(required = false) Integer age,
                                              @RequestParam(required = false) String sortBy){
        return "Hard coded Query parameter are age: "+age+" and SortBy filter method: "+sortBy;
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
