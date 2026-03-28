package org.sanjeet.springbootweb.services;

import org.modelmapper.ModelMapper;
import org.sanjeet.springbootweb.dto.EmployeeDTO;
import org.sanjeet.springbootweb.entities.EmployeeEntities;
import org.sanjeet.springbootweb.exceptions.ResourceNotFoundException;
import org.sanjeet.springbootweb.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<EmployeeDTO> getEmployeeById(Long id){
        Optional<EmployeeEntities> employeeEntity = employeeRepository.findById(id);
//        ModelMapper mapper = new ModelMapper();
        return employeeEntity.map(employee -> mapper.map(employee, EmployeeDTO.class));
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
        boolean exists = isEmployeeExistsById(id);
        if(!exists) throw new ResourceNotFoundException("Can't update the employee because employee doesn't exist with id "+id);
        EmployeeEntities employeeEntity = mapper.map(inputEmployee, EmployeeEntities.class);
        employeeEntity.setId(id);
        EmployeeEntities savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isEmployeeExistsById(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long id){
        boolean exists = isEmployeeExistsById(id);
        if(!exists) throw new ResourceNotFoundException("Can't delete the employee because employee doesn't exist with id "+id);
        employeeRepository.deleteById(id);
        return true;
    }
    public void deleteAllEmployee(){
        employeeRepository.deleteAll();
    }

    public EmployeeDTO updatePartialEmployee(Long employeeId, Map<String, Object> updates){
        boolean exists = isEmployeeExistsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Can't partially update the employee because employee doesn't exist with id "+employeeId);

        EmployeeEntities employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) ->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntities.class, field);
            if(fieldToBeUpdated!=null){
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            }
        });
        return  mapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
