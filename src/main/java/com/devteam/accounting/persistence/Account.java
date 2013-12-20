package com.devteam.accounting.persistence;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
@SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "CODE")
    private String code;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", version=" + version +
                ", code='" + code + '\'' +
                '}';
    }
}
