package com.devteam.accounting.service;

import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.web.dto.AccountDto;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
