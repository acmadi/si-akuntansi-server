package com.devteam.accounting.dao.generic;

import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.service.helper.OrderDir;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 8:56 AM
 */
public abstract class GenericDaoImpl<T, TId extends Serializable> implements GenericDao<T, TId> {

    private Class<T> persistentClass;
    private Class<TId> idClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected GenericDaoImpl() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        idClass = (Class<TId>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T findById(TId id) {
        return (T) getCurrentSession().get(getPersistentClass(), id);
    }

    @Override
    public T loadById(Long id) {
        return (T) getCurrentSession().load(getPersistentClass(), id);
    }

    @Override
    public void save(T entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
        getCurrentSession().flush();
        getCurrentSession().refresh(entity);
    }

    @Override
    public List<T> findAlls() {
        String queryString = String.format("FROM %s", getPersistentClass().getCanonicalName());
        Query query = getCurrentSession().createQuery(queryString);
        return query.list();
    }

    @Override
    public List<T> findAlls(String property, OrderDir orderDir) {
        String queryString = String.format("FROM %s ORDER BY %s %s",
                getPersistentClass().getCanonicalName(),
                property, orderDir);
        Query query = getCurrentSession().createQuery(queryString);
        return query.list();
    }

    @Override
    public List<T> findAlls(int start, int count) {
        String queryString = String.format("FROM %s ORDER BY %s %s");
        Query query = getCurrentSession().createQuery(queryString);
        query.setFirstResult(start);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public List<T> findAlls(String property, OrderDir orderDir, int start, int count) {
        String queryString = String.format("FROM %s ORDER BY %s %s",
                getPersistentClass().getCanonicalName(),
                property, orderDir);
        Query query = getCurrentSession().createQuery(queryString);
        query.setFirstResult(start);
        query.setMaxResults(count);
        return query.list();
    }


    @Override
    public Long countAlls() {
        String queryString = String.format("SELECT  COUNT(entity) FROM %s entity", getPersistentClass().getCanonicalName());
        Query query = getCurrentSession().createQuery(queryString);
        return (Long) query.uniqueResult();
    }

    @Override
    public void removeById(Long id) {
        T entity = (T) getCurrentSession().load(getPersistentClass(), id);
        getCurrentSession().delete(entity);
    }

    @Override
    public void removeAll() {
        String queryString = String.format("DELETE FROM %s entity", getPersistentClass().getCanonicalName());
        Query query = getCurrentSession().createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public void refresh(T entity) {
        getCurrentSession().refresh(entity);
    }

}
