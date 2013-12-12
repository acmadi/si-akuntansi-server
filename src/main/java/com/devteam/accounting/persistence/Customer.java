package com.devteam.accounting.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

public class Customer implements Serializable {

    private Long id;
    private Timestamp version;
    private String name;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
