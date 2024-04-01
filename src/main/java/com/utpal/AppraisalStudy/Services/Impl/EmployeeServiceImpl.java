package com.utpal.AppraisalStudy.Services.Impl;

import com.utpal.AppraisalStudy.Entity.Attributes;
import com.utpal.AppraisalStudy.DTO.AttributeDTO;
import com.utpal.AppraisalStudy.DTO.EmployeeDTO;
import com.utpal.AppraisalStudy.DTO.EmployeeWithListDTO;
import com.utpal.AppraisalStudy.DTO.TaskDTO;
import com.utpal.AppraisalStudy.Entity.Employees;
import com.utpal.AppraisalStudy.Exceptions.UserAlreadyExists;
import com.utpal.AppraisalStudy.Exceptions.UserNotFoundException;
import com.utpal.AppraisalStudy.Repository.EmployeeRepository;
import com.utpal.AppraisalStudy.Services.Interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDTO saveEmployees(Employees employees) throws UserAlreadyExists {
        Optional<Employees> emp1= employeeRepository.findByEmail(employees.getEmail());

        if(emp1.isEmpty()){
            String password = passwordEncoder.encode(employees.getPassword());
            employees.setPassword(password);
            Employees emp =  employeeRepository.save(employees);
            return modelMapper.map(emp, EmployeeDTO.class);
        }else{
            throw new UserAlreadyExists("Email already Exists...");
        }

    }

    @Override
    public List<EmployeeWithListDTO> getAllEmployees() {
        List<Employees> emp = employeeRepository.findAllByOrderByIdAsc().stream()
                .filter(employee -> !"admin".equalsIgnoreCase(employee.getDesignation()))
                .toList();
        return emp.stream().map(this::mapEmployeeWithSortedTasks)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployee(long Id) {
        Optional<Employees> emp = employeeRepository.findById(Id);

        if(emp.isPresent()){
            Employees employees = emp.get();
            return modelMapper.map(employees, EmployeeDTO.class);
        }else{
            throw new UserNotFoundException("Employee Not found with Id : " + Id);
        }
    }

    @Override
    public EmployeeWithListDTO getEmployeeDetails(long id) {
        Optional<Employees> emp = employeeRepository.findById(id);
        if(emp.isPresent()){
            Employees employees = emp.get();
            return modelMapper.map(mapEmployeeWithSortedTasks(employees), EmployeeWithListDTO.class);
        }else{
            throw new UserNotFoundException("Employee Not found with Id : " + id);
        }
    }

    @Override
    public boolean updateNotifyByAdmin(long id, Employees employees) {
        Optional<Employees> emp = employeeRepository.findById(id);

        if(emp.isPresent()){
            Employees employees1 = emp.get();
            employees1.setNoifybyadmin(employees.isNoifybyadmin());
            employeeRepository.save(employees1);
            return true;
        }else{
            throw new UserNotFoundException("Employee Not found with Id : " + id);
        }
    }

    @Override
    public AttributeDTO saveAttribute(long id, AttributeDTO attributeDTO) {
        Optional<Employees> optionalEmployees = employeeRepository.findById(id);

        if(optionalEmployees.isPresent()){
            Employees employees = optionalEmployees.get();
            if (employees.getAttributes() != null) {
                Attributes existingAttributes = employees.getAttributes();
                modelMapper.map(attributeDTO, existingAttributes);
                employeeRepository.save(employees);
                return attributeDTO;
            } else {

                Attributes newAttributes = modelMapper.map(attributeDTO, Attributes.class);
                newAttributes.setEmployees(employees);
                employees.setAttributes(newAttributes);
                Employees updatedEmployee = employeeRepository.save(employees);
                Attributes updatedAttributes = updatedEmployee.getAttributes();
                return modelMapper.map(updatedAttributes, AttributeDTO.class);
            }
        }else{
            throw new UserNotFoundException("Employee Not found with Id : " + id);
        }
    }

    @Override
    public List<EmployeeWithListDTO> searchEmployees(String s) {
        List<Employees> emp = employeeRepository.searchEmployee(s)
                .stream()
                .filter(employee -> !"admin".equalsIgnoreCase(employee.getDesignation()))
                .toList();
        return emp.stream().map(this::mapEmployeeWithSortedTasks)
                .collect(Collectors.toList());
    }

    private EmployeeWithListDTO mapEmployeeWithSortedTasks(Employees employees) {
        EmployeeWithListDTO employeeDTO = this.modelMapper.map(employees, EmployeeWithListDTO.class);

        List<TaskDTO> sortedTasks = employeeDTO.getTasks().stream()
                .sorted(Comparator.comparingLong(TaskDTO::getId))
                .toList();

        employeeDTO.setTasks(new ArrayList<>(sortedTasks));

        return employeeDTO;
    }
}
