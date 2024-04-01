package com.utpal.AppraisalStudy.Services;

import com.utpal.AppraisalStudy.Exceptions.UserNotFoundException;
import com.utpal.AppraisalStudy.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser =  employeeRepository.findByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("Email not found"));

       // System.out.println(List.of(new SimpleGrantedAuthority(appUser.isAdmin() ? "ADMIN":"Employee")));
//        List.of(new SimpleGrantedAuthority(appUser.isAdmin() ? "ADMIN":"Employee"))
        return new User(appUser.getEmail(), appUser.getPassword(), List.of(new SimpleGrantedAuthority(appUser.isAdmin() ? "ADMIN":"Employee")));
    }
}
