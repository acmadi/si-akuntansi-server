package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.Country;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
@Repository
public class CountryDaoImpl extends GenericDaoImpl<Country, Long> implements CountryDao {

    @Override
    public List<Country> findByIso2(String code) {
        Query query = getCurrentSession().createQuery("FROM Country c WHERE c.iso2 LIKE :code");
        query.setParameter("code", code);
        return query.list();
    }

    @Override
    public List<Country> findByIso3(String code) {
        Query query = getCurrentSession().createQuery("FROM Country c WHERE c.iso3 LIKE :code");
        query.setParameter("code", code);
        return query.list();
    }

    @Override
    public List<Country> findByKeyword(List<com.devteam.accounting.web.controller.params.Order> orders, int start, int count, String keyword) {
        Criteria criteria = getCurrentSession().createCriteria(Country.class);
        criteria.add(Restrictions.ilike("name", keyword));

        for (com.devteam.accounting.web.controller.params.Order order : orders) {
            criteria.addOrder(order.toHibernateOrder());
        }

        criteria.setFirstResult(start);
        criteria.setMaxResults(count);
        return criteria.list();
    }

    @Override
    public Long countByKeyword(String keyword) {
        Criteria criteria = getCurrentSession().createCriteria(Country.class);
        criteria.add(Restrictions.ilike("name", keyword));
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();

    }


}
