package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.Account;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account, Long> implements AccountDao {

    @Override
    public List<Account> findByCode(String code) {
        Query query = getCurrentSession().createQuery("FROM Account a WHERE a.code LIKE :code");
        query.setParameter("code", code);
        return query.list();
    }

    @Override
    public Long countByCode(String code) {
        Query query = getCurrentSession().createQuery("SELECT COUNT(a) FROM Account a WHERE a.code LIKE :code");
        query.setParameter("code", code);
        return (Long) query.uniqueResult();
    }

}
