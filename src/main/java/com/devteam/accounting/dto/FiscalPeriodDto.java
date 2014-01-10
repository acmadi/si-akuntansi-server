package com.devteam.accounting.dto;

import com.devteam.accounting.dto.component.MonthYearDto;

import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:14 PM
 */

public class FiscalPeriodDto implements Serializable {
    private Long id;
    private Long version;
    private MonthYearDto start;
    private MonthYearDto end;

    public FiscalPeriodDto() {
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

    public MonthYearDto getStart() {
        return start;
    }

    public void setStart(MonthYearDto start) {
        this.start = start;
    }

    public MonthYearDto getEnd() {
        return end;
    }

    public void setEnd(MonthYearDto end) {
        this.end = end;
    }
}
