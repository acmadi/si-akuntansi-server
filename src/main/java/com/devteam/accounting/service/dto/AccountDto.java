package com.devteam.accounting.service.dto;

import java.io.Serializable;

public class AccountDto implements Serializable {
    private Long id;
    private Long version;
    private String code;
    private String name;
    private String description;
    private AccountTypeDto type;
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

    public AccountTypeDto getType() {
        return type;
    }

    public void setType(AccountTypeDto type) {
        this.type = type;
    }

    public AccountDto getParent() {
        return parent;
    }

    public void setParent(AccountDto parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", version=" + version +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", parent=" + parent +
                '}';
    }
}
