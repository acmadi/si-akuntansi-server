package com.devteam.accounting.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:34 PM
 */
public class CurrencyDto implements Serializable {
    private Long id;
    private Long version;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String symbol;

    private CountryDto country;

    public CurrencyDto() {
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }
}
