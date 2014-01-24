package com.devteam.accounting.service;

import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.dto.FiscalPeriodDto;
import com.devteam.accounting.service.helper.OrderDir;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.params.Order;

import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:07 AM
 */
public interface FiscalPeriodService {

    void save(FiscalPeriodDto fiscalPeriod);

    void update(FiscalPeriodDto fiscalPeriod);

    void removeById(Long id);

    FiscalPeriodDto findById(Long id);

    QueryResult<FiscalPeriodDto> findAlls();

    QueryResult<FiscalPeriodDto> findAlls(List<Order> orders, int start, int count);


}
