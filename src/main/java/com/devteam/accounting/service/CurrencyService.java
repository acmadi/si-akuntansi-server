package com.devteam.accounting.service;

import com.devteam.accounting.dto.CurrencyDto;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.rest.params.Order;

import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:07 AM
 */
public interface CurrencyService {

    void save(CurrencyDto currency);

    void update(CurrencyDto currency);

    CurrencyDto findById(Long id);

    QueryResult<CurrencyDto> findByKeyword(List<Order> orders, int start, int count, String keyword);

    QueryResult<CurrencyDto> findAlls(int start, int count);

    QueryResult<CurrencyDto> findAlls(List<Order> orderes, int start, int count);

    void removeById(Long id);

}
