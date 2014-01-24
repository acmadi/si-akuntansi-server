package com.devteam.accounting.dao;

import com.devteam.accounting.dao.generic.GenericDaoImpl;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.persistence.FiscalPeriod;
import com.devteam.accounting.service.helper.OrderDir;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * User: pancara
 * Date: 12/19/13
 * Time: 8:26 AM
 */
@Repository
public class FiscalPeriodDaoImpl extends GenericDaoImpl<FiscalPeriod, Long> implements FiscalPeriodDao {

}
