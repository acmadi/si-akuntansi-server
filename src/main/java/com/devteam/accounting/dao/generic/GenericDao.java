package com.devteam.accounting.dao.generic;

import com.devteam.accounting.persistence.Currency;
import com.devteam.accounting.web.controller.rest.params.Order;

import java.io.Serializable;
import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 8:55 AM
 */
public interface GenericDao<T, TId extends Serializable> {

    T findById(final TId id);

    T loadById(final Long id);

    void save(T entity);

    void update(T entity);

    List<T> findAlls();

    Long countAlls();

    void removeById(Long id);

    void removeAll();

    void refresh(T entity);

    void flush();

    List<T> findAlls(List<Order> orders);

    List<T> findAlls(List<Order> orders, int start, int count);

    List<T> findAlls(int start, int count);

    void evict(Currency entity);
}
