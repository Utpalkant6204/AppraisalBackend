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

        Set<SimpleGrantedAuthority> p;
        if(appUser.isAdmin()){
            p = Collections.singleton(new SimpleGrantedAuthority("Employee"));
        }
        else{
            p = Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
        }
        return new User(appUser.getEmail(), appUser.getPassword(), p);
    }
}
