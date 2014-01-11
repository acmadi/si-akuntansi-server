package com.devteam.accounting.persistence;

import com.devteam.accounting.persistence.component.MonthYear;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:14 PM
 */

@Entity
@Table(name = "fiscal_period")
@Audited
@SequenceGenerator(name = "fiscal_period_seq", sequenceName = "fiscal_period_seq", allocationSize = 1)
public class FiscalPeriod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fiscal_period_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "month", column = @Column(name = "start_month")),
            @AttributeOverride(name = "year", column = @Column(name = "start_year"))
    })
    private MonthYear start;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "month", column = @Column(name = "end_month")),
            @AttributeOverride(name = "year", column = @Column(name = "end_year"))
    })
    private MonthYear end;

    public FiscalPeriod() {
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

    public MonthYear getStart() {
        return start;
    }

    public void setStart(MonthYear start) {
        this.start = start;
    }

    public MonthYear getEnd() {
        return end;
    }

    public void setEnd(MonthYear end) {
        this.end = end;
    }
}
