package com.devteam.accounting.service;

import com.devteam.accounting.dto.CountryDto;

import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:07 AM
 */
public interface CountryService {

    void save(CountryDto country);

//    void update(CountryDto country);

    CountryDto findById(Long id);

    List<CountryDto> findAlls();

    void deleteById(Long id);

}
