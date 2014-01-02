package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.AccountType;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 10:44 PM
 */
@Repository
public class AccountTypeDaoImpl extends GenericDaoImpl<AccountType, Long> implements AccountTypeDao {

    @Override
    public List<AccountType> findByCode(String code) {
        Query query = getCurrentSession().createQuery("FROM AccountType at WHERE at.code LIKE :code");
        query.setParameter("code", code);
        return query.list();
    }


}
