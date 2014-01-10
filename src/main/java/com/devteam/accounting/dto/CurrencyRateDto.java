package com.devteam.accounting.dto;

import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 6:36 PM
 */
public class CurrencyRateDto implements Serializable {

    private Long id;
    private Long version;
    private CurrencyDto source;
    private CurrencyDto target;
    private Double rate;

    public CurrencyRateDto() {
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

    public CurrencyDto getSource() {
        return source;
    }

    public void setSource(CurrencyDto source) {
        this.source = source;
    }

    public CurrencyDto getTarget() {
        return target;
    }

    public void setTarget(CurrencyDto target) {
        this.target = target;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
