package com.devteam.accounting.dao;

import com.devteam.accounting.persistence.AccountType;
import com.devteam.accounting.service.dto.AccountTypeDto;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 10:46 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml"})
public class AccountTypeDaoImplTest {
    @Autowired
    private AccountTypeDao accountTypeDao;

    @Test
    @Transactional
    public void save() {
        AccountType entity = new AccountType();
        entity.setCode("AT-001");
        entity.setName("Account type sample");
        accountTypeDao.save(entity);
        assertNotNull(entity.getId());
    }

    @Test
    @Transactional(readOnly = false)
    public void update() {
        AccountType type = new AccountType();
        type.setCode("T-001");
        type.setName("Type sample 1");
        accountTypeDao.save(type);

        assertNotNull(type.getId());

        type.setName("Type sample 1 Modified");
        accountTypeDao.update(type);

        assertEquals(1, type.getVersion().intValue());

    }
}
