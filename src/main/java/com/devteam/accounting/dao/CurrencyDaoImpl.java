package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.persistence.Currency;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
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
public class CurrencyDaoImpl extends GenericDaoImpl<Currency, Long> implements CurrencyDao {

    @Override
    public List<Currency> findByKeyword(List<com.devteam.accounting.web.controller.rest.params.Order> orders, int start, int count, String keyword) {
        Criteria criteria = getCurrentSession().createCriteria(Currency.class);

        Criterion crCode = Restrictions.ilike("code", keyword);
        Criterion crName = Restrictions.ilike("name", keyword);
        Criterion crOr = Restrictions.or(crCode, crName);
        criteria.add(crOr);

        for (com.devteam.accounting.web.controller.rest.params.Order order : orders) {
            criteria.addOrder(order.toHibernateOrder());
        }

        criteria.setFirstResult(start);
        criteria.setMaxResults(count);
        return criteria.list();
    }

    @Override
    public Long countByKeyword(String keyword) {
        Criteria criteria = getCurrentSession().createCriteria(Country.class);
        Criterion crCode = Restrictions.ilike("code", keyword);
        Criterion crName = Restrictions.ilike("name", keyword);
        Criterion crOr = Restrictions.or(crCode, crName);
        criteria.add(crOr);

        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();

    }


}
