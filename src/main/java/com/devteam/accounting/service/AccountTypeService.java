package com.devteam.accounting.service;

import com.devteam.accounting.service.dto.AccountDto;
import com.devteam.accounting.service.dto.AccountTypeDto;

import java.util.List;

/**
 * User: pancara
 * Date: 12/31/13
 * Time: 10:58 PM
 */
public interface AccountTypeService {
    void save(AccountTypeDto type);

    void update(AccountTypeDto type);

    AccountTypeDto findById(Long id);

    List<AccountTypeDto> findByCode(String keyword);
}
