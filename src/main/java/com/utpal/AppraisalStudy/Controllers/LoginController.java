package com.utpal.AppraisalStudy.Controllers;

import com.utpal.AppraisalStudy.DTO.ChangePasswordDTO;
import com.utpal.AppraisalStudy.DTO.EmployeeDTO;
import com.utpal.AppraisalStudy.DTO.LoginDTO;
import com.utpal.AppraisalStudy.DTO.LoginResponseDTO;
import com.utpal.AppraisalStudy.Entity.Employees;
import com.utpal.AppraisalStudy.Exceptions.PasswordMatcher;
import com.utpal.AppraisalStudy.Exceptions.UserAlreadyExists;
import com.utpal.AppraisalStudy.Services.Interfaces.AuthService;
import com.utpal.AppraisalStudy.Services.Interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AuthService loginService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> getLoginInfo(@RequestBody LoginDTO loginDTO){
        LoginResponseDTO loginResponseDTO = loginService.checkValidation(loginDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody Employees employees) throws UserAlreadyExists {
        EmployeeDTO emp = employeeService.saveEmployees(employees);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws PasswordMatcher {
        boolean res = employeeService.changePassword(changePasswordDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
