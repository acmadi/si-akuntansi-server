package com.devteam.accounting.service.wrapper;

import java.util.List;

/**
 * User: pancara
 * Date: 1/8/14
 * Time: 10:40 AM
 */
public class QueryResult<T> {

    private Long count;
    private List<T> data;

    public QueryResult() {
    }

    public QueryResult(Long count, List<T> data) {
        this.count = count;
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
