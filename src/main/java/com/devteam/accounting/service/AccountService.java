package com.devteam.accounting.service;

import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.service.wrapper.QueryResult;

import java.util.List;

/**
 * User: pancara
 * Date: 12/20/13
 * Time: 9:07 AM
 */
public interface AccountService {

    void save(AccountDto account);

    void update(AccountDto account);

    AccountDto findById(Long id);

    QueryResult findByCode(String code);

    QueryResult findAlls();

    void deleteById(Long id);
}
