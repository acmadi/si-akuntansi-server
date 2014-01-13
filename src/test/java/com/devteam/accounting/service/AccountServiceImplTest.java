package com.devteam.accounting.service;

import com.devteam.accounting.dto.AccountDto;

import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.util.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
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
        AccountDto dto = new AccountDto();
        String code = Helper.createStringWithLength(4);
        dto.setCode(code);
        dto.setName("Child 001");
        dto.setDescription("first child");
        accountService.save(dto);

        AccountDto acc = accountService.findById(dto.getId());
        String newCode = Helper.createStringWithLength(5);
        acc.setCode(newCode);
        accountService.update(acc);

        acc = accountService.findById(acc.getId());
        assertEquals(newCode, acc.getCode());
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
        accountService.removeAll();

        AccountDto dto = new AccountDto();
        dto.setCode("C001");
        dto.setName("Child 001");
        dto.setDescription("first child");
        accountService.save(dto);

        accountService.deleteById(dto.getId());
        dto = accountService.findById(dto.getId());
        assertNull(dto);
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
