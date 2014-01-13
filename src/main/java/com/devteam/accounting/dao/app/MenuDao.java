package com.devteam.accounting.dao.app;

import com.devteam.accounting.dao.generic.GenericDao;
import com.devteam.accounting.persistence.app.Menu;
import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuType;

import java.util.List;

/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
public interface MenuDao extends GenericDao<Menu, Long> {

    List<Menu> findByType(MenuType type);

    Long countByType(MenuType type);

    List<Menu> getRootByLocationAndParent(MenuLocation loc, Menu parent);

}
