package com.devteam.accounting.service;

import com.devteam.accounting.dto.AccountTypeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 11:02 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml"})
public class AccountTypeServiceImplTest {

    @Autowired
    private AccountTypeService accountTypeService;

    @Test
    public void save() throws Exception {
        AccountTypeDto dto = new AccountTypeDto();
        dto.setCode("ACT-001");
        dto.setName("Account type 001");
        accountTypeService.save(dto);
    }

    @Test
    public void update() throws Exception {
        AccountTypeDto old = new AccountTypeDto();
        old.setCode("ACT-001");
        old.setName("Account type 001");
        accountTypeService.save(old);

        AccountTypeDto dto = accountTypeService.findById(1L);
        dto.setCode("ACT-002");
        dto.setName("Account type 002");
        accountTypeService.update(dto);
    }

    @Test
    public void findByCode() {
        for (int i = 1; i <= 10; i++) {
            AccountTypeDto type = new AccountTypeDto();
            type.setCode("ACT-00" + i);
            type.setName("Account type 00" + i);
            accountTypeService.save(type);
        }

        List<AccountTypeDto> types = accountTypeService.findByCode("10");
        assertEquals(types.size(), 1);
    }
}
