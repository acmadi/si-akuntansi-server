package com.devteam.accounting.persistence;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:34 PM
 */
@Entity
@Table(name = "COUNTRY")
@Audited
@SequenceGenerator(name = "country_seq", sequenceName = "country_seq", allocationSize = 1)
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "iso2", unique = true)
    private String iso2;

    @Column(name = "iso3", unique = true)
    private String iso3;

    @Column(name = "name")
    private String name;

    public Country() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != null ? !id.equals(country.id) : country.id != null) return false;
        if (iso2 != null ? !iso2.equals(country.iso2) : country.iso2 != null) return false;
        if (iso3 != null ? !iso3.equals(country.iso3) : country.iso3 != null) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (iso2 != null ? iso2.hashCode() : 0);
        result = 31 * result + (iso3 != null ? iso3.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
