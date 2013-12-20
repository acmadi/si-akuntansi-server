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

    public Account findById(Long id) {
        return (Account) getCurrentSession().get(Account.class, id);
    }

    @Override
    public List<Account> findByCode(String code) {
        Query query = getCurrentSession().createQuery("FROM Account WHERE code LIKE :code");
        query.setParameter("code", code);
        return query.list();
    }
}
