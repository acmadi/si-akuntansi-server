package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDao;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.service.helper.OrderDir;

import java.util.List;

/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
public interface CountryDao extends GenericDao<Country, Long> {

    List<Country> findByIso2(String code);

    List<Country> findByIso3(String code);

    List<Country> findByKeyword(String orderProperty, OrderDir orderDir, int start, int count, String keyword);

    Long countByKeyword(String keyword);
}
