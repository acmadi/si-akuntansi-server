package com.devteam.accounting.persistence;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 1/2/14
 * Time: 8:33 AM
 */
@Entity
@Table(name = "ROLE")
@Audited
@SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name", unique = true)
    private String name;

    public Role() {
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
