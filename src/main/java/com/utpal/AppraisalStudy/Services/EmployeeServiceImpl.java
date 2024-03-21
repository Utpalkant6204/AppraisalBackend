package com.utpal.AppraisalStudy.Services;

import com.utpal.AppraisalStudy.Entity.Attributes;
import com.utpal.AppraisalStudy.Entity.DTO.AttributeDTO;
import com.utpal.AppraisalStudy.Entity.DTO.EmployeeDTO;
import com.utpal.AppraisalStudy.Entity.DTO.EmployeeWithListDTO;
import com.utpal.AppraisalStudy.Entity.DTO.TaskDTO;
import com.utpal.AppraisalStudy.Entity.Employees;
import com.utpal.AppraisalStudy.Exceptions.UserNotFoundException;
import com.utpal.AppraisalStudy.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDTO saveEmployees(Employees employees) {
        String password = passwordEncoder.encode(employees.getPassword());
        employees.setPassword(password);
        Employees emp =  employeeRepository.save(employees);
        EmployeeDTO edto = modelMapper.map(emp, EmployeeDTO.class);
        return edto;
    }

    @Override
    public List<EmployeeWithListDTO> getAllEmployees() {
        List<Employees> emp = employeeRepository.findAllByOrderByIdAsc();
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
            Attributes attributes = modelMapper.map(attributeDTO, Attributes.class);
            attributes.setEmployees(employees);
            employees.setAttributes(attributes);
            Employees e1 = employeeRepository.save(employees);
            Attributes attributeDTO1 = e1.getAttributes();
            return modelMapper.map(attributeDTO1, AttributeDTO.class);
        }else{
            throw new UserNotFoundException("Employee Not found with Id : " + id);
        }
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
