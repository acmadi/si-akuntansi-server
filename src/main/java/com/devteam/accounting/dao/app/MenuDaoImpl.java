package com.devteam.accounting.dao.app;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.app.Menu;
import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
@Repository
public class MenuDaoImpl extends GenericDaoImpl<Menu, Long> implements MenuDao {

    @Override
    public List<Menu> findByType(MenuType type) {
        Query query = getCurrentSession().createQuery("FROM Menu m WHERE m.type = :type");
        query.setParameter("type", type);
        return query.list();
    }

    @Override
    public Long countByType(MenuType type) {
        Query query = getCurrentSession().createQuery("SELECT COUNT(m) FROM Menu m WHERE m.type = :type");
        query.setParameter("type", type);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Menu> getRootByLocationAndParent(MenuLocation loc, Menu parent) {
        Query query = null;
        if (parent == null) {
            query = getCurrentSession().createQuery("FROM Menu m WHERE (m.location = :loc) AND (m.parent IS null)");
            query.setParameter("loc", loc);
        } else {
            query = getCurrentSession().createQuery("FROM Menu m WHERE (m.location = :location) AND (m.parent = :parent)");
            query.setParameter("parent", parent);
            query.setParameter("loc", loc);
        }
        return query.list();
    }


}
