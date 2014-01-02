package com.devteam.accounting.mapping.converter;

import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.persistence.AccountType;
import com.devteam.accounting.service.dto.AccountDto;
import com.devteam.accounting.service.dto.AccountTypeDto;
import org.dozer.DozerConverter;

/**
 * User: pancara
 * Date: 1/1/14
 * Time: 12:24 AM
 */

public class AccountConverter extends DozerConverter<Account, AccountDto> {
    private int maxDepth = 1;

    public AccountConverter() {
        super(Account.class, AccountDto.class);
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public AccountDto convertTo(Account source, AccountDto destination) {
        if (source != null) {
            destination = new AccountDto();
        }
        convertTo(source, destination, 0);
        return destination;
    }

    private void convertTo(Account source, AccountDto destination, int depth) {
        destination.setId(source.getId());
        destination.setVersion(source.getVersion());
        destination.setCode(source.getCode());
        destination.setName(source.getName());
        destination.setDescription(source.getDescription());

        // type
        if (source.getType() != null) {
            AccountTypeDto type = new AccountTypeDto();
            type.setId(source.getType().getId());
            type.setVersion(source.getType().getVersion());
            type.setCode(source.getType().getCode());
            type.setName(source.getType().getName());
            destination.setType(type);
        } else {
            destination.setType(null);
        }

        // account parent
        if (depth < maxDepth) {
            if (source.getParent() != null) {
                destination.setParent(new AccountDto());
                convertTo(source.getParent(), destination.getParent(), depth + 1);
            } else {
                destination.setParent(null);
            }
        }
    }

    @Override
    public Account convertFrom(AccountDto source, Account destination) {

        destination.setId(source.getId());
        destination.setVersion(source.getVersion());
        destination.setCode(source.getCode());
        destination.setName(source.getName());
        destination.setDescription(source.getDescription());

        // type
        if (source.getType() != null) {
            AccountType type = new AccountType();
            type.setId(source.getType().getId());
            type.setVersion(source.getType().getVersion());
            type.setCode(source.getType().getCode());
            type.setName(source.getType().getName());
            destination.setType(type);
        } else {
            destination.setType(null);
        }

        // account parent
        if (source.getParent() != null) {
            destination.setParent(new Account());
            convertFrom(source.getParent(), destination.getParent());
        } else {
            destination.setParent(null);
        }

        return destination;
    }
}
