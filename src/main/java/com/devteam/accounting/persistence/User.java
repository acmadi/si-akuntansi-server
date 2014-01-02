package com.devteam.accounting.persistence;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: pancara
 * Date: 1/2/14
 * Time: 8:17 AM
 */

@Entity
@Table(name = "USERS")
@Audited
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "user_password")
    private String password;


    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;


    @ManyToMany()
    @JoinTable(name = "USER_ROLE", joinColumns = {@JoinColumn(name = "user")}, inverseJoinColumns = {@JoinColumn(name = "role")})
    private Set<Role> roles = new HashSet<>();

    public User() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
