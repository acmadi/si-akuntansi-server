package com.devteam.accounting.service;

import com.devteam.accounting.dao.CountryDao;
import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.mapping.MappingUtil;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.persistence.Country;
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
        if (dto.getId() == null) {
            Country country = new Country();
            mappingUtil.map(dto, country);
            countryDao.save(country);
            mappingUtil.map(country, dto);
        } else {
            Country country = countryDao.findById(dto.getId());

            if (!country.getVersion().equals(dto.getVersion())) {
                throw new OptimisticLockException();
            }

            mappingUtil.map(dto, country);
            countryDao.update(country);
            mappingUtil.map(country, dto);
        }
    }

    @Transactional(readOnly = true)
    public CountryDto findById(Long id) {
        Country country = countryDao.findById(id);
        return (country == null) ? null : mappingUtil.map(country, CountryDto.class);
    }


    @Override
    @Transactional(readOnly = true)
    public List<CountryDto> findAlls() {
        List<Country> countries = countryDao.findAlls();
        return mappingUtil.mapList(countries, CountryDto.class);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        countryDao.removeById(id);
    }

}
