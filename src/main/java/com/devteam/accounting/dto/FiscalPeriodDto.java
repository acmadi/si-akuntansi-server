package com.devteam.accounting.dto;

import com.devteam.accounting.dto.component.MonthYearDto;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:14 PM
 */

public class FiscalPeriodDto implements Serializable {
    private Long id;
    private Long version;

    @NotNull
    @Valid
    private MonthYearDto startAt = new MonthYearDto();

    @NotNull
    @Valid
    private MonthYearDto endAt = new MonthYearDto();

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

    public MonthYearDto getStartAt() {
        return startAt;
    }

    public void setStartAt(MonthYearDto startAt) {
        this.startAt = startAt;
    }

    public MonthYearDto getEndAt() {
        return endAt;
    }

    public void setEndAt(MonthYearDto endAt) {
        this.endAt = endAt;
    }
}
