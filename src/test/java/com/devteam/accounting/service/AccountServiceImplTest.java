package com.devteam.accounting.service;

import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.web.dto.AccountDto;
import org.hibernate.SessionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:46 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans.xml"})

public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void findById() {
        AccountDto acc = accountService.findById(10L);
        assertNotNull(acc);
    }

    @Test
    public void update() {
        AccountDto dto = new AccountDto();
        dto.setCode("ACC-0001xxx ");
        accountService.save(dto);

        AccountDto acc = accountService.findById(dto.getId());
        acc.setCode("KODE ");
        accountService.update(acc);
    }

    @Test
    public void testSave() throws Exception {
        AccountDto dto = new AccountDto();
        dto.setCode("ACC-0001xxx");
        accountService.save(dto);
    }

    @Test
    public void findByCode() {
        List<AccountDto> accounts = accountService.findByCode("ACC%");
        for (AccountDto acc : accounts) {
            System.out.println(acc.getCode());
        }
    }

    @Test

    public void findAlls() {
        List<AccountDto> accounts = accountService.findAlls();
    }
}
