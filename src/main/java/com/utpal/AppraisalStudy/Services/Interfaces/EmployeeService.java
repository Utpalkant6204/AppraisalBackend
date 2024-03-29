package com.utpal.AppraisalStudy.Services.Interfaces;

import com.utpal.AppraisalStudy.DTO.AttributeDTO;
import com.utpal.AppraisalStudy.DTO.EmployeeDTO;
import com.utpal.AppraisalStudy.DTO.EmployeeWithListDTO;
import com.utpal.AppraisalStudy.Entity.Employees;

import java.util.List;

public interface EmployeeService {
    public EmployeeDTO saveEmployees(Employees employees);


    public List<EmployeeWithListDTO> getAllEmployees();

    public EmployeeDTO getEmployee(long Id);

    public EmployeeWithListDTO getEmployeeDetails(long id);


    public boolean updateNotifyByAdmin(long id, Employees employees);

    public AttributeDTO saveAttribute(long id, AttributeDTO attributeDTO);

    public List<EmployeeWithListDTO> searchEmployees(String s);
}
