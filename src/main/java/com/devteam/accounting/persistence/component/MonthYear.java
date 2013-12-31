package com.devteam.accounting.persistence.component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 5:14 PM
 */

@Embeddable
public class MonthYear implements Serializable {

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "year", nullable = false)
    private Integer year;

    public MonthYear() {
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

    @Override
    public String toString() {
        return "MonthYear{" +
                "month=" + month +
                ", year=" + year +
                '}';
    }
}
