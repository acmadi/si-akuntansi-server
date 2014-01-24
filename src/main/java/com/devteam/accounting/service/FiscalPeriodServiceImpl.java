package com.devteam.accounting.service;

import com.devteam.accounting.dao.CountryDao;
import com.devteam.accounting.dao.FiscalPeriodDao;
import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.dto.FiscalPeriodDto;
import com.devteam.accounting.mapping.MappingUtil;
import com.devteam.accounting.persistence.Country;
import com.devteam.accounting.persistence.FiscalPeriod;
import com.devteam.accounting.service.helper.OrderDir;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.params.Order;
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
public class FiscalPeriodServiceImpl implements FiscalPeriodService {

    @Autowired
    private FiscalPeriodDao fiscalPeriodDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(FiscalPeriodDto dto) {
        FiscalPeriod entity = new FiscalPeriod();
        mappingUtil.map(dto, entity);
        fiscalPeriodDao.save(entity);
        mappingUtil.map(entity, dto);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(FiscalPeriodDto dto) {
        FiscalPeriod entity = fiscalPeriodDao.findById(dto.getId());
        if (!entity.getVersion().equals(dto.getVersion())) {
            throw new OptimisticLockException();
        }

        mappingUtil.map(dto, entity);
        fiscalPeriodDao.update(entity);
        mappingUtil.map(entity, dto);
    }


    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public FiscalPeriodDto findById(Long id) {
        FiscalPeriod entity = fiscalPeriodDao.findById(id);
        return (entity == null) ? null : mappingUtil.map(entity, FiscalPeriodDto.class);
    }

    @Override
    public QueryResult<FiscalPeriodDto> findAlls() {
        List<FiscalPeriod> entities = fiscalPeriodDao.findAlls();
        List<FiscalPeriodDto> dto = mappingUtil.mapList(entities, FiscalPeriodDto.class);
        Long total = fiscalPeriodDao.countAlls();
        return new QueryResult(total, dto);
    }


    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public QueryResult<FiscalPeriodDto> findAlls(List<Order> orders, int start, int count) {
        List<FiscalPeriod> entities = fiscalPeriodDao.findAlls(orders, start, count);
        List<FiscalPeriodDto> dto = mappingUtil.mapList(entities, FiscalPeriodDto.class);
        Long total = fiscalPeriodDao.countAlls();
        return new QueryResult(total, dto);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void removeById(Long id) {
        fiscalPeriodDao.removeById(id);
    }


}
