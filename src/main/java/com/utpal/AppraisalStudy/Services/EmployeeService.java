package com.utpal.AppraisalStudy.Services;

import com.utpal.AppraisalStudy.Entity.DTO.EmployeeDTO;
import com.utpal.AppraisalStudy.Entity.DTO.EmployeeWithListDTO;
import com.utpal.AppraisalStudy.Entity.Employees;

import java.util.List;

public interface EmployeeService {
    public EmployeeDTO saveEmployees(Employees employees);


    public List<EmployeeWithListDTO> getAllEmployees();

    public EmployeeDTO getEmployee(long Id);

    public EmployeeWithListDTO getEmployeeDetails(long id);


    public boolean updateNotifyByAdmin(long id, Employees employees);
}
