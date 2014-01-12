package com.devteam.accounting.service.app;

import com.devteam.accounting.dao.app.MenuDao;
import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.dto.app.MenuDto;
import com.devteam.accounting.mapping.MappingUtil;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.persistence.AccountType;
import com.devteam.accounting.persistence.app.Menu;
import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuType;
import com.devteam.accounting.service.wrapper.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User: pancara
 * Date: 1/11/14
 * Time: 11:43 PM
 */

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public MenuDto findById(Long id) {
        Menu menu = menuDao.findById(id);
        return mappingUtil.map(menu, MenuDto.class);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<MenuDto> getRootByLocation(MenuLocation loc) {
        List<Menu> menus = menuDao.getRootByLocationAndParent(loc, null);
        return mappingUtil.mapList(menus, MenuDto.class);
    }


    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(MenuDto dto) {
        Menu menu = new Menu();
        mappingUtil.map(dto, menu);
        setReferencedEntity(dto, menu);

        menuDao.save(menu);
        mappingUtil.map(menu, dto);
    }


    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(MenuDto dto) {
        Menu menu = menuDao.findById(dto.getId());

        mappingUtil.map(dto, menu);
        setReferencedEntity(dto, menu);

        menuDao.save(menu);
        mappingUtil.map(menu, dto);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void removeById(Long id) {
        menuDao.removeById(id);
    }

    private void setReferencedEntity(MenuDto dto, Menu menu) {
        // set parent
        if (dto.getParent() != null) {
            Menu parent = menuDao.loadById(dto.getParent().getId());
            menu.setParent(parent);
        } else {
            menu.setParent(null);
        }
    }

}
