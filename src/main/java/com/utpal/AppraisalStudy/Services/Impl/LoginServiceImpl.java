package com.utpal.AppraisalStudy.Services.Impl;

import com.utpal.AppraisalStudy.Entity.DTO.LoginDTO;
import com.utpal.AppraisalStudy.Entity.DTO.LoginResponseDTO;
import com.utpal.AppraisalStudy.Entity.Employees;
import com.utpal.AppraisalStudy.Exceptions.UserNotFoundException;
import com.utpal.AppraisalStudy.Repository.EmployeeRepository;
import com.utpal.AppraisalStudy.Services.Interfaces.AuthService;
import com.utpal.AppraisalStudy.Utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements AuthService {

    @Autowired
    private EmployeeRepository employeeRepository;
//
    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public LoginResponseDTO checkValidation(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        System.out.println(email + "  " + password);

//        Optional<Optional<Employees>> empOptional = Optional.ofNullable(employeeRepository.findByEmail(email));
//
//        if (empOptional.isPresent()) {
//            Optional<Employees> emp = empOptional.get();
//            if (emp.getEmail().equals(email) && passwordEncoder.matches(password,emp.getPassword())) {
//                LoginResponseDTO loginResponseDTO = modelMapper.map(emp, LoginResponseDTO.class);
//                loginResponseDTO.setMessage("success");
//                loginResponseDTO.setStatus((long) HttpStatus.FOUND.value());
//                return loginResponseDTO;
//            } else {
//               throw new UserNotFoundException("Invalid Password");
//            }
//        } else {
//            throw new UserNotFoundException("user Not Found");
//        }
        var authToken = new UsernamePasswordAuthenticationToken(email, password);
        var authenticate = authenticationManager.authenticate(authToken);
        System.out.println(authenticate.getName());

        Optional<Employees> employees = employeeRepository.findByEmail(authenticate.getName());
        if(employees.isPresent()){
            Employees emp = employees.get();


            LoginResponseDTO loginResponseDTO = modelMapper.map(emp, LoginResponseDTO.class);
            loginResponseDTO.setMessage("success");;
            loginResponseDTO.setStatus((long) HttpStatus.FOUND.value());
            loginResponseDTO.setToken(JwtUtils.generateToken(authenticate.getName()));
            return loginResponseDTO;
        }
        else{
            throw  new UserNotFoundException("User Not Found");
        }
    }
}
