package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDao;
import com.devteam.accounting.persistence.AccountType;

import java.util.List;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 10:44 PM
 */
public interface AccountTypeDao extends GenericDao<AccountType, Long> {
    List<AccountType> findByCode(String code);
}
