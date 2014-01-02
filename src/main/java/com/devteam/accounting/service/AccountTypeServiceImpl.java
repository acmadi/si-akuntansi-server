package com.devteam.accounting.service;

import com.devteam.accounting.dao.AccountTypeDao;
import com.devteam.accounting.persistence.AccountType;
import com.devteam.accounting.service.dto.AccountTypeDto;
import com.devteam.accounting.mapping.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 10:58 PM
 */

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountTypeDao accountTypeDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(AccountTypeDto dto) {
        AccountType type = new AccountType();
        mappingUtil.map(dto, type);
        accountTypeDao.save(type);
        mappingUtil.map(type, dto);
    }


    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(AccountTypeDto dto) {
        AccountType type = accountTypeDao.findById(dto.getId());
        mappingUtil.map(dto, type);
        accountTypeDao.update(type);
        mappingUtil.map(type, dto);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public AccountTypeDto findById(Long id) {
        AccountType type = accountTypeDao.findById(id);
        return (type == null) ? null : mappingUtil.map(type, AccountTypeDto.class);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<AccountTypeDto> findByCode(String code) {
        String keyword = "%" + code + "%";
        List<AccountType> types = accountTypeDao.findByCode(keyword);
        return mappingUtil.mapList(types, AccountTypeDto.class);
    }

}
