package com.devteam.accounting.service;

import com.devteam.accounting.service.dto.AccountDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:46 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml"})

public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void findById() {
        AccountDto dto = new AccountDto();
        dto.setCode("P-001 ");
        accountService.save(dto);

        AccountDto acc = accountService.findById(dto.getId());
        assertEquals(dto.getCode(), acc.getCode());
    }

    @Test
    public void update() {
        AccountDto acc = accountService.findById(1L);
        acc.setCode("P 0001");
        accountService.update(acc);
    }

    @Test
    public void testSave() throws Exception {
        AccountDto parent = new AccountDto();
        parent.setCode("P001");
        parent.setName("Parent 1");
        parent.setDescription("Parent 1 of child 1");
        parent.setParent(null);
        accountService.save(parent);

        AccountDto child = new AccountDto();
        child.setCode("C001");
        child.setName("Child 001");
        child.setDescription("first child");
        child.setParent(parent);
        accountService.save(child);
    }


    @Test
    public void testDeleteById() {
        accountService.deleteById(4L);
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
        for(AccountDto dto : accounts) {
            System.out.println(dto);
        }
    }
}
