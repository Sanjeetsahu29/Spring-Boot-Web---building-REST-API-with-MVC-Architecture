package org.sanjeet.springbootweb.services;

import org.sanjeet.springbootweb.entities.EmployeeEntities;
import org.sanjeet.springbootweb.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    // this class will talk to talk the database
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntities getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public List<EmployeeEntities> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public EmployeeEntities createEmployee(EmployeeEntities employeeEntity){
        return employeeRepository.save(employeeEntity);
    }
}
