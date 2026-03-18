package org.sanjeet.springbootweb.services;

import org.modelmapper.ModelMapper;
import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.sanjeet.springbootweb.entities.EmployeeEntities;
import org.sanjeet.springbootweb.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    // this class will talk to talk the database
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    // Manual mapping from EmployeeEntity object to EmployeeDTO is error-prone and a lot of code repeats
    // Instead of manual mapping we will use external dependency to handle this manual mapping from one object type to another object type
//    public EmployeeDTO getEmployeeById(Long id){
//        EmployeeEntities employeeEntity = employeeRepository.findById(id).orElse(null);
//        return new EmployeeDTO(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getEmail(), employeeEntity.getAge(), employeeEntity.getDateOfJoining(), employeeEntity.getIsActive());
//    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntities employeeEntity = employeeRepository.findById(id).orElse(null);
//        ModelMapper mapper = new ModelMapper();
        return mapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeEntities> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public EmployeeEntities createEmployee(EmployeeEntities employeeEntity){
        return employeeRepository.save(employeeEntity);
    }
}
