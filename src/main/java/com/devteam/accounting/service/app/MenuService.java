package com.devteam.accounting.service.app;

import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.dto.app.MenuDto;
import com.devteam.accounting.persistence.app.Menu;
import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuType;
import com.devteam.accounting.service.wrapper.QueryResult;

import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:07 AM
 */
public interface MenuService {

    MenuDto findById(Long id);

    List<MenuDto> getRootByLocation(MenuLocation loc);

    void save(MenuDto dto);

    void update(MenuDto dto);

    void removeById(Long id);
}
