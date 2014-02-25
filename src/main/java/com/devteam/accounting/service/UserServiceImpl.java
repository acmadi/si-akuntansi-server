package com.devteam.accounting.service;

import com.devteam.accounting.dao.FiscalPeriodDao;
import com.devteam.accounting.dao.UserDao;
import com.devteam.accounting.dto.FiscalPeriodDto;
import com.devteam.accounting.mapping.MappingUtil;
import com.devteam.accounting.persistence.FiscalPeriod;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.rest.params.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:08 AM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public boolean validateUser(String userId, String password) {
        return userDao.validateUser(userId, password);
    }

}
