package com.utpal.AppraisalStudy.Services.Interfaces;

import com.utpal.AppraisalStudy.Entity.DTO.LoginDTO;
import com.utpal.AppraisalStudy.Entity.DTO.LoginResponseDTO;

public interface AuthService {
    public LoginResponseDTO checkValidation(LoginDTO loginDTO);
}
