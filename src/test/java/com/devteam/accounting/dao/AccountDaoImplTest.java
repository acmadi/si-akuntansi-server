package com.devteam.accounting.dao;

import com.devteam.accounting.persistence.Account;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertNotNull;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:29 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml"})
public class AccountDaoImplTest {
    @Autowired
    private AccountDao accountDao;

    @Test
    @Transactional
    public void save() {
        Account account = new Account();
        account.setCode("ACC-0001");
        accountDao.save(account);
        assertNotNull(account.getId());
    }

    @Test
    @Transactional
    public void findById() {
        Account newAcc = new Account();
        newAcc.setCode("ACC-0001x");
        accountDao.save(newAcc);
        assertNotNull(newAcc.getId());

        Account acc = accountDao.findById(newAcc.getId());
        assertNotNull(acc);
    }
}
