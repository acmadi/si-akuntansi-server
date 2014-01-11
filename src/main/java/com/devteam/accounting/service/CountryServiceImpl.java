package com.devteam.accounting.service;

import com.devteam.accounting.dao.CountryDao;
import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.mapping.MappingUtil;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.service.wrapper.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OptimisticLockException;
import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:08 AM
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(CountryDto dto) {
        Country country = new Country();
        mappingUtil.map(dto, country);
        countryDao.save(country);
        mappingUtil.map(country, dto);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(CountryDto dto) {
        Country country = countryDao.findById(dto.getId());
        if (!country.getVersion().equals(dto.getVersion())) {
            throw new OptimisticLockException();
        }

        mappingUtil.map(dto, country);
        countryDao.update(country);
        mappingUtil.map(country, dto);
    }


    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public CountryDto findById(Long id) {
        Country country = countryDao.findById(id);
        return (country == null) ? null : mappingUtil.map(country, CountryDto.class);
    }


    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public QueryResult<CountryDto> findAlls() {
        List<Country> entities = countryDao.findAlls();
        List<CountryDto> data = mappingUtil.mapList(entities, CountryDto.class);
        Long count = countryDao.countAlls();
        return new QueryResult(count, data);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void deleteById(Long id) {
        countryDao.removeById(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void removeById(Long id) {
        countryDao.removeById(id);
    }

}
