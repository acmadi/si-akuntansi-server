package com.devteam.accounting.dto.component;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:14 PM
 */

public class MonthYearDto implements Serializable {

    @NotNull
    private Integer month;

    @NotNull
    private Integer year;

    public MonthYearDto() {
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}