package com.devteam.accounting.service.helper;

/**
 * User: pancara
 * Date: 1/23/14
 * Time: 10:56 AM
 */
public class PropertyOrder {
    private String property;
    private OrderDir orderDir;

    public PropertyOrder() {
    }

    public PropertyOrder(String property, OrderDir orderDir) {
        this.property = property;
        this.orderDir = orderDir;
    }

    public String getProperty() {
        return property;
    }

    public OrderDir getOrderDir() {
        return orderDir;
    }
}
