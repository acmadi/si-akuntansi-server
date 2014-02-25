package com.devteam.accounting.dto.form;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * User: pancara
 * Date: 1/16/14
 * Time: 11:56 AM
 */
public class LoginDto implements Serializable {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;

    public LoginDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
