package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.persistence.app.Menu;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
@Repository
public class MenuDaoImpl extends GenericDaoImpl<Menu, Long> implements MenuDao {

}
