package com.devteam.accounting.web.dto;

import java.io.Serializable;

public class AccountDto implements Serializable {

    private Long id;
    private Long version;
    private String code;
    private String name;
    private String description;
    private AccountDto parent;

    public AccountDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountDto getParent() {
        return parent;
    }

    public void setParent(AccountDto parent) {
        this.parent = parent;
    }
}
