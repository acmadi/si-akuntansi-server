package com.devteam.accounting.service;

import com.devteam.accounting.dao.AccountDao;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.util.MappingUtil;
import com.devteam.accounting.web.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:08 AM
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(AccountDto dto) {
        Account account = new Account();
        mappingUtil.map(dto, account);
        accountDao.save(account);
        mappingUtil.map(account, dto);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(AccountDto dto) {
        Account account = accountDao.findById(dto.getId());
        mappingUtil.map(dto, account);
        accountDao.update(account);
        mappingUtil.map(account, dto);
    }

    @Transactional(readOnly = true)
    public AccountDto findById(Long id) {
        Account acc = accountDao.findById(id);
        System.out.println(acc);

        return (acc == null) ? null : mappingUtil.map(acc, AccountDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> findByCode(String code) {
        List<Account> accounts = accountDao.findByCode(code);
        return mappingUtil.mapList(accounts, AccountDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> findAlls() {
        List<Account> accounts = accountDao.findAlls();
        return mappingUtil.mapList(accounts, AccountDto.class);
    }
}
