package com.devteam.accounting.dao;

import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.spring.PersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:29 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class}, loader = AnnotationConfigContextLoader.class)
public class AccountDaoImplTest {
    @Autowired
    private AccountDao accountDao;

    @Test
    @Transactional
    public void save() {
        Account account = new Account();
        account.setCode("ACC-0001");
        accountDao.save(account);
    }

    @Test
    @Transactional
    public void findById() {
        Account account = accountDao.findById(1L);
        System.out.println(account.getCode());
    }
}
