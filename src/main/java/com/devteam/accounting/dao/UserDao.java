package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDao;
import com.devteam.accounting.persistence.User;

/**
 * User: pancara
 * Date: 1/27/14
 * Time: 9:50 AM
 */
public interface UserDao extends GenericDao<User, Long> {

    boolean validateUser(String userId, String password);
}
