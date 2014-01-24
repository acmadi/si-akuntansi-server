package com.devteam.accounting.web.controller.params;

import com.devteam.accounting.service.helper.OrderDir;

/**
 * User: pancara
 * Date: 1/24/14
 * Time: 5:55 PM
 */
public class Order {
    private String property;
    private OrderDir ordering;

    public Order() {
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public OrderDir getOrdering() {
        return ordering;
    }

    public void setOrdering(OrderDir ordering) {
        this.ordering = ordering;
    }

    public org.hibernate.criterion.Order toHibernateOrder() {
        return ordering == OrderDir.ASC ?
                org.hibernate.criterion.Order.asc(property) :
                org.hibernate.criterion.Order.desc(property);
    }
}
