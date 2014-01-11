package com.devteam.accounting.mapping;

import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.persistence.Account;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * User: pancara
 * Date: 1/11/14
 * Time: 1:11 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/mapper.xml"})
public class MappingUtilTest {
    @Autowired
    private MappingUtil mappingUtil;

    @Test
    public void map() {
        Account account = new Account();
        account.setId(1L);
        account.setParent(new Account());
        account.getParent().setId(2L);

        AccountDto dto = new AccountDto();
        mappingUtil.map(account, dto);
        assertEquals(dto.getId(), account.getId());
        assertEquals(dto.getParent().getId(), account.getParent().getId());

        dto = mappingUtil.map(account, AccountDto.class);
        assertEquals(dto.getId(), account.getId());
        assertEquals(dto.getParent().getId(), account.getParent().getId());

    }
}
