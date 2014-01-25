package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDao;
import com.devteam.accounting.persistence.Currency;
import com.devteam.accounting.web.controller.rest.params.Order;

import java.util.List;

/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
public interface CurrencyDao extends GenericDao<Currency, Long> {

    List<Currency> findByKeyword(List<Order> orders, int start, int count, String keyword);

    Long countByKeyword(String keyword);
}
