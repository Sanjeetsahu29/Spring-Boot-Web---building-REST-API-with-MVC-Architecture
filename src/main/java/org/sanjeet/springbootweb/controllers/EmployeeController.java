package org.sanjeet.springbootweb.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.sanjeet.springbootweb.entities.EmployeeEntities;
import org.sanjeet.springbootweb.exceptions.ResourceNotFoundException;
import org.sanjeet.springbootweb.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(employeeId);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for ID: "+employeeId));
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeDTO> > getAllEmployees(@RequestParam(required = false, name="inputAge") Integer age,
                                                  @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }
    // Since this is the controller method of type post, so you can't be able to hit this
    // api request from the browser url. We need a client that can mimic our frontend client
    // Using POSTMAN we can make api request of different HTTP methods
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployeeInfo){
         EmployeeDTO savedEmployee = employeeService.createEmployee(inputEmployeeInfo);
         return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping()
    public String getMessageWithQueryParameter(@RequestParam(required = false) Integer age,
                                              @RequestParam(required = false) String sortBy){
        return "Hard coded Query parameter are age: "+age+" and SortBy filter method: "+sortBy;
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(
            @PathVariable Long employeeId,
            @RequestBody EmployeeDTO inputEmployee){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, inputEmployee));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted =  employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping()
    public ResponseEntity<Boolean> deleteAllEmployee(){
        employeeService.deleteAllEmployee();
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId
                                                 ){
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployee(employeeId, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
