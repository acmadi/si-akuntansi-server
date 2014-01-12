package com.devteam.accounting.mapping.converter;

import com.devteam.accounting.persistence.type.MenuType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: pancara
 * Date: 1/12/14
 * Time: 9:44 AM
 */
public class MenuTypeConverterTest {
    private MenuTypeConverter converter;

    @Before
    public void setUp() {
        converter = new MenuTypeConverter();
    }

    @Test
    public void testConvert() throws Exception {
        MenuType type = (MenuType) converter.convert(null, MenuType.MENU_ITEM.name(), MenuType.class, String.class);
        assertEquals(MenuType.MENU_ITEM, type);

        String strType = (String) converter.convert(null, MenuType.MENU_ITEM, String.class, MenuType.class);
        assertEquals(MenuType.MENU_ITEM.name(), strType);
    }
}
