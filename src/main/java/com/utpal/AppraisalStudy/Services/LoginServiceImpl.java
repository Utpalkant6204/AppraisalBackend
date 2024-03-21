package com.utpal.AppraisalStudy.Services;

import com.utpal.AppraisalStudy.Entity.DTO.LoginDTO;
import com.utpal.AppraisalStudy.Entity.DTO.LoginResponseDTO;
import com.utpal.AppraisalStudy.Entity.Employees;
import com.utpal.AppraisalStudy.Exceptions.UserNotFoundException;
import com.utpal.AppraisalStudy.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public LoginResponseDTO checkValidation(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        System.out.println(email + "  " + password);

        Optional<Employees> empOptional = Optional.ofNullable(employeeRepository.findByEmail(email));

        if (empOptional.isPresent()) {
            Employees emp = empOptional.get();
            if (emp.getEmail().equals(email) && passwordEncoder.matches(password,emp.getPassword())) {
                LoginResponseDTO loginResponseDTO = modelMapper.map(emp, LoginResponseDTO.class);
                loginResponseDTO.setMessage("success");
                loginResponseDTO.setStatus((long) HttpStatus.FOUND.value());
                return loginResponseDTO;
            } else {
               throw new UserNotFoundException("Invalid Password");
            }
        } else {
            throw new UserNotFoundException("user Not Found");
        }
    }
}
