package com.devteam.accounting.service;

import com.devteam.accounting.dto.AccountDto;

import com.devteam.accounting.service.wrapper.QueryResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        dto.setCode("P-004 ");
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
        AccountDto parent = accountService.findById(1L);
//        parent.setCode("P001");
//        parent.setName("Parent 1");
//        parent.setDescription("Parent 1 of child 1");
//        parent.setParent(null);
//        accountService.save(parent);

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
        QueryResult<AccountDto> result = accountService.findByCode("ACC%");
        for (AccountDto acc : result.getData()) {
            System.out.println(acc.getCode());
        }
    }

    @Test
    public void findAlls() {
        QueryResult<AccountDto> result = accountService.findAlls();
        System.out.println(result.getCount());
        for (AccountDto dto : result.getData()) {
            System.out.println(dto);
        }
    }
}
