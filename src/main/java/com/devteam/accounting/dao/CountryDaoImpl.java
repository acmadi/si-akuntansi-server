package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.persistence.Country;
import org.hibernate.Query;
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


}
