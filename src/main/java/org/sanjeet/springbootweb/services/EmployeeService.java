package org.sanjeet.springbootweb.services;

import org.modelmapper.ModelMapper;
import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.sanjeet.springbootweb.entities.EmployeeEntities;
import org.sanjeet.springbootweb.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntities> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map((employeeEntity) -> mapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee){
        EmployeeEntities toSaveEntity = mapper.map(inputEmployee, EmployeeEntities.class);
        EmployeeEntities savedEntity = employeeRepository.save(toSaveEntity);
        return mapper.map(savedEntity, EmployeeDTO.class);
    }
    public EmployeeDTO updateEmployeeById( Long id, EmployeeDTO inputEmployee){
        EmployeeEntities employeeEntity = mapper.map(inputEmployee, EmployeeEntities.class);
        employeeEntity.setId(id);
        EmployeeEntities savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id){
        try {
            boolean exist = employeeRepository.existsById(id);
            if(!exist) return false;
            employeeRepository.deleteById(id);
            return true;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
