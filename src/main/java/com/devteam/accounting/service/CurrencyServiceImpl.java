package com.devteam.accounting.service;

import com.devteam.accounting.dao.CountryDao;
import com.devteam.accounting.dao.CurrencyDao;
import com.devteam.accounting.dto.CurrencyDto;
import com.devteam.accounting.mapping.MappingUtil;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.persistence.Currency;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.rest.params.Order;
import org.springframework.transaction.annotation.Isolation;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:08 AM
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(CurrencyDto dto) {
        Currency entity = new Currency();
        applyDtoProperty(dto, entity);
        currencyDao.save(entity);
        mappingUtil.map(entity, dto);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(CurrencyDto dto) {
        Currency entity = currencyDao.findById(dto.getId());
        if (!entity.getVersion().equals(dto.getVersion())) {
            throw new OptimisticLockException();
        }

        applyDtoProperty(dto, entity);
        currencyDao.update(entity);
        mappingUtil.map(entity, dto);
    }

    private void applyDtoProperty(CurrencyDto dto, Currency target) {
        target.setId(dto.getId());
        target.setVersion(dto.getVersion());
        target.setCode(dto.getCode());
        target.setName(dto.getName());
        target.setSymbol(dto.getSymbol());

        if (dto.getCountry() != null && dto.getCountry().getId() != null) {
            Country country = countryDao.loadById(dto.getCountry().getId());
            target.setCountry(country);
        } else {
            target.setCountry(null);
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public CurrencyDto findById(Long id) {
        Currency entity = currencyDao.findById(id);
        return (entity == null) ? null : mappingUtil.map(entity, CurrencyDto.class);
    }


    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public QueryResult<CurrencyDto> findAlls(int start, int count) {
        List<Currency> entities = currencyDao.findAlls(start, count);
        List<CurrencyDto> data = mappingUtil.mapList(entities, CurrencyDto.class);
        Long total = currencyDao.countAlls();
        return new QueryResult(total, data);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public QueryResult<CurrencyDto> findAlls(List<Order> orders, int start, int count) {
        List<Currency> entities = currencyDao.findAlls(orders, start, count);

        List<CurrencyDto> dto = mappingUtil.mapList(entities, CurrencyDto.class);
        Long total = currencyDao.countAlls();
        return new QueryResult(total, dto);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public QueryResult<CurrencyDto> findByKeyword(List<Order> orders, int start, int count, String keyword) {
        List<Currency> entities = currencyDao.findByKeyword(orders, start, count, keyword);
        List<CurrencyDto> dto = mappingUtil.mapList(entities, CurrencyDto.class);
        Long total = currencyDao.countByKeyword(keyword);
        return new QueryResult(total, dto);
    }


    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void removeById(Long id) {
        currencyDao.removeById(id);
    }


}
