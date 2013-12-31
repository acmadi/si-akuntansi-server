package com.devteam.accounting.persistence;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 6:36 PM
 */
@Entity
@Table(name = "CURRENCY_RATE")
@Audited
@SequenceGenerator(name = "currency_rate_seq", sequenceName = "currency_rate_seq", allocationSize = 1)
public class CurrencyRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_rate_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @ManyToOne
    @JoinColumn(name = "source")
    private Currency source;

    @ManyToOne
    @JoinColumn(name = "target")
    private Currency target;

    @Column(name = "rate")
    private Double rate;

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

    public Currency getSource() {
        return source;
    }

    public void setSource(Currency source) {
        this.source = source;
    }

    public Currency getTarget() {
        return target;
    }

    public void setTarget(Currency target) {
        this.target = target;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
