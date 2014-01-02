package com.devteam.accounting.service;

import com.devteam.accounting.service.dto.AccountDto;

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

    List<AccountDto> findByCode(String code);

    List<AccountDto> findAlls();

    void deleteById(Long id);
}
