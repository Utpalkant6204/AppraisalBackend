package com.utpal.AppraisalStudy.Exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

public class UserAlreadyExists extends SQLIntegrityConstraintViolationException {
    public UserAlreadyExists(String msg) {
        super(msg);
    }

    public UserAlreadyExists() {
    }
}
