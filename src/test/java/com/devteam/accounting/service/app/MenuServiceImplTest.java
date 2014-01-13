package com.devteam.accounting.service.app;

import com.devteam.accounting.dto.app.MenuDto;
import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: pancara
 * Date: 1/12/14
 * Time: 11:16 AM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml"})
public class MenuServiceImplTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void saveMenu() {
        MenuDto dto = new MenuDto();
        dto.setCaption("Menu 1");
        dto.setPath("#");
        dto.setIndex(1);
        dto.setDescription("first child");
        dto.setParent(null);
        dto.setType(MenuType.MENU_ITEM.name());
        dto.setLocation(MenuLocation.TOP.name());
        menuService.save(dto);
    }

    @Test
    public void saveChild() {
        MenuDto parent = new MenuDto();
        parent.setCaption("Menu 1");
        parent.setPath("#");
        parent.setIndex(1);
        parent.setDescription("first child");
        parent.setParent(null);
        parent.setType(MenuType.MENU_ITEM.name());
        parent.setLocation(MenuLocation.TOP.name());
        menuService.save(parent);

        MenuDto dto = new MenuDto();
        dto.setCaption("Menu 1.1");
        dto.setPath("#");
        dto.setIndex(2);
        dto.setDescription("first child");
        dto.setParent(parent);
        dto.setType(MenuType.MENU_ITEM.name());
        dto.setLocation(MenuLocation.TOP.name());
        menuService.save(dto);
    }


    @Test
    public void getByLocation() {
        menuService.removeAll();

        MenuDto parent = new MenuDto();
        parent.setCaption("Menu 1");
        parent.setPath("#");
        parent.setIndex(1);
        parent.setDescription("first child");
        parent.setParent(null);
        parent.setType(MenuType.MENU_ITEM.name());
        parent.setLocation(MenuLocation.TOP.name());
        menuService.save(parent);

        List<MenuDto> menus = menuService.getRootByLocation(MenuLocation.TOP);
        assertEquals(1, menus.size());
    }

}