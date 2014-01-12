package com.devteam.accounting.mapping;

import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.dto.app.MenuDto;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.persistence.app.Menu;
import com.devteam.accounting.persistence.type.MenuType;
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
    public void mapAccount() {
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

    @Test
    public void mapMenu() {

        Menu menu = new Menu();
        menu.setId(1L);
        menu.setCaption("menu 1");
        menu.setType(MenuType.MENU_ITEM);

        MenuDto dto = mappingUtil.map(menu, MenuDto.class);
        assertEquals(menu.getId(), dto.getId());
        assertEquals(menu.getCaption(), dto.getCaption());
        assertEquals(MenuType.MENU_ITEM.name(), dto.getType());

         dto = new MenuDto();

        mappingUtil.map(menu, dto);
        assertEquals(menu.getId(), dto.getId());
        assertEquals(menu.getCaption(), dto.getCaption());
        assertEquals(MenuType.MENU_ITEM.name(), dto.getType());

    }
}
