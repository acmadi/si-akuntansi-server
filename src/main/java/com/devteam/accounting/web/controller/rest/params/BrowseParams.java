package com.devteam.accounting.web.controller.rest.params;

import java.util.LinkedList;
import java.util.List;

/**
 * User: pancara
 * Date: 1/24/14
 * Time: 7:27 PM
 */
public class BrowseParams {

    private List<Order> orders = new LinkedList<>();
    private int start;
    private int count;
    private String keyword;

    public BrowseParams() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
