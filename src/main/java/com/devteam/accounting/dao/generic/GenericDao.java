package com.devteam.accounting.dao.generic;

import com.devteam.accounting.persistence.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 8:55 AM
 */
public interface GenericDao<T, TId extends Serializable> {

    T findById(final TId id);

    void save(T entity);

    void update(T entity);

    List<T> findAlls();
}
