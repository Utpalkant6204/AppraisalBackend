package com.utpal.AppraisalStudy.Services.Interfaces;

import com.utpal.AppraisalStudy.DTO.LoginDTO;
import com.utpal.AppraisalStudy.DTO.LoginResponseDTO;

public interface AuthService {
    public LoginResponseDTO checkValidation(LoginDTO loginDTO);
}
