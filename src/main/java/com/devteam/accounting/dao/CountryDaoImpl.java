package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.service.helper.OrderDir;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
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
    public List<Country> findByKeyword(String orderProperty, OrderDir orderDir, int start, int count, String keyword) {
        Criteria criteria = getCurrentSession().createCriteria(Country.class);
        criteria.add(Restrictions.ilike("name", keyword));

        Order order = orderDir == OrderDir.ASC ?
                Order.asc(orderProperty) : Order.desc(orderProperty);
        criteria.addOrder(order);

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
