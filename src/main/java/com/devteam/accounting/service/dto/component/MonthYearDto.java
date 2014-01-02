package com.devteam.accounting.service.dto.component;

import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:14 PM
 */

public class MonthYearDto implements Serializable {
    private Integer month;
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