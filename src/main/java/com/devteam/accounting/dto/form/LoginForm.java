package com.devteam.accounting.dto.form;

import java.io.Serializable;

/**
 * User: pancara
 * Date: 1/16/14
 * Time: 11:56 AM
 */
public class LoginForm implements Serializable {
    private String userId;
    private String password;

    public LoginForm() {
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
