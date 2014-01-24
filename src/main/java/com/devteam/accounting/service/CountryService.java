package com.devteam.accounting.service;

import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.service.helper.OrderDir;
import com.devteam.accounting.service.helper.PropertyOrder;
import com.devteam.accounting.service.wrapper.QueryResult;

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

    QueryResult<CountryDto> findAlls(String orderProperty, OrderDir orderDir, int start, int count);

    void removeById(Long id);

    void deleteAll();

    QueryResult findByKeyword(String orderProperty, OrderDir orderDir, int start, int count, String keyword);

}
