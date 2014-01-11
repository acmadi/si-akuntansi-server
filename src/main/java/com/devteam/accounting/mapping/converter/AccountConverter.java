package com.devteam.accounting.mapping.converter;

import com.devteam.accounting.persistence.Account;
import com.devteam.accounting.persistence.AccountType;
import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.dto.AccountTypeDto;
import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.MappingException;

/**
 * User: pancara
 * Date: 1/1/14
 * Time: 12:24 AM
 */

public class AccountConverter implements CustomConverter {
    private int maxDepth = 1;

    public AccountConverter() {
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }
        Account acc ;
        AccountDto dto;
        if (source instanceof Account) {
            acc = (Account) source;
            if (destination == null) {
                dto = new AccountDto();
            } else {
                dto = (AccountDto) destination;
            }
            return convertTo(acc, dto);

        } else if (source instanceof AccountDto) {
            dto = (AccountDto) source;

            if (destination == null) {
                acc = new Account();
            } else {
                acc = (Account) destination;
            }
            return convertFrom(dto, acc);
        } else {
            throw new MappingException("Converter TestCustomConverter "
                    + "used incorrectly. Arguments passed in were:"
                    + destination + " and " + source);
        }
    }

    public AccountDto convertTo(Account source, AccountDto destination) {
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
        if (source.getParent() != null) {
            destination.setParent(new AccountDto());
            convertTo_Parent(source.getParent(), destination.getParent(), 0);
        } else {
            destination.setParent(null);
        }

        return destination;
    }

    private AccountDto convertTo_Parent(Account source, AccountDto destination, int depth) {

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
        if (source.getParent() != null) {
            if (depth < maxDepth) {
                destination.setParent(new AccountDto());
                convertTo_Parent(source.getParent(), destination.getParent(), 0);
            }
        } else {
            destination.setParent(null);
        }


        return destination;
    }

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
