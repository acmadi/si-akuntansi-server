package com.devteam.accounting.service;

import com.devteam.accounting.dao.AccountDao;
import com.devteam.accounting.dao.AccountTypeDao;
import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.mapping.MappingUtil;
import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.persistence.AccountType;
import com.devteam.accounting.service.wrapper.QueryResult;
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
    private AccountTypeDao accountTypeDao;

    @Autowired
    private MappingUtil mappingUtil;

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(AccountDto dto) {
        Account account = new Account();
        mappingUtil.map(dto, account);
        setForeignKey(dto, account);

        accountDao.save(account);
        mappingUtil.map(account, dto);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(AccountDto dto) {
        Account account = accountDao.findById(dto.getId());

        mappingUtil.map(dto, account);
        setForeignKey(dto, account);

        accountDao.update(account);
        mappingUtil.map(account, dto);
    }

    private void setForeignKey(AccountDto dto, Account account) {
        // set parent
        if (dto.getParent() != null) {
            Account parent = accountDao.loadById(dto.getParent().getId());
            account.setParent(parent);
        } else {
            account.setParent(null);
        }

        // set type
        if (account.getType() != null) {
            AccountType type = accountTypeDao.loadById(dto.getType().getId());
            account.setType(type);
        } else {
            account.setType(null);
        }
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public AccountDto findById(Long id) {
        Account acc = accountDao.findById(id);
        return (acc == null) ? null : mappingUtil.map(acc, AccountDto.class);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public QueryResult findByCode(String code) {
        List<Account> accounts = accountDao.findByCode(code);
        List<AccountDto> data = mappingUtil.mapList(accounts, AccountDto.class);
        Long count = accountDao.countByCode(code);
        return new QueryResult(count, data);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public QueryResult findAlls() {
        List<Account> accounts = accountDao.findAlls();
        List<AccountDto> data = mappingUtil.mapList(accounts, AccountDto.class);
        Long count = accountDao.countAlls();
        return new QueryResult(count, data);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        accountDao.removeById(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void removeById(Long id) {
        accountDao.removeById(id);
    }
}
