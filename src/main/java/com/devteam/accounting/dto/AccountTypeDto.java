package com.devteam.accounting.dto;

import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 10:35 PM
 */

public class AccountTypeDto implements Serializable {
    private Long id;
    private Long version;
    private String code;
    private String name;

    public AccountTypeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
