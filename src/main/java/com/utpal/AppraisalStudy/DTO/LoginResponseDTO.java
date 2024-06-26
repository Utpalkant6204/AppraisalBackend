package com.utpal.AppraisalStudy.DTO;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;

public class LoginResponseDTO {

    private long id;
    private String email;

    private boolean isAdmin;

    @Nullable
    private String message;

    private long Status;

    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    public long getStatus() {
        return Status;
    }

    public void setStatus(Long status) {
        Status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(long status) {
        Status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
