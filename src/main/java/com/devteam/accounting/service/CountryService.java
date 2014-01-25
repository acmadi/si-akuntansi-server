package com.devteam.accounting.service;

import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.rest.params.Order;

import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:07 AM
 */
public interface CountryService {

    void save(CountryDto country);

    void update(CountryDto country);

    CountryDto findById(Long id);

    QueryResult<CountryDto> findAlls(int start, int count);

    QueryResult<CountryDto> findAlls(List<Order> orders);

    QueryResult<CountryDto> findAlls(List<Order> orders, int start, int count);

    void removeById(Long id);

    void deleteAll();

    QueryResult findByKeyword(List<Order> orders, int start, int count, String keyword);

}
