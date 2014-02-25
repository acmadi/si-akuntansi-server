package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.FiscalPeriod;
import com.devteam.accounting.persistence.User;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public boolean validateUser(String userId, String password) {
        String queryString = "FROM User u WHERE u.userId = :userId";
        Query query = getCurrentSession().createQuery(queryString);
        query.setParameter("userId", userId);
        query.setMaxResults(1);
        User user = (User) query.uniqueResult();
        return (user != null) && StringUtils.equals(user.getPassword(), password);

    }
}
