package com.devteam.accounting.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:34 PM
 */
public class CountryDto implements Serializable {
    private Long id;
    private Long version;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Size(min = 2, max = 2)
    private String iso2;

    @NotNull
    @Size(min = 3, max = 3)
    private String iso3;

    public CountryDto() {
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

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    @Override
    public String toString() {
        return "CountryDto{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", iso2='" + iso2 + '\'' +
                ", iso3='" + iso3 + '\'' +
                '}';
    }
}
