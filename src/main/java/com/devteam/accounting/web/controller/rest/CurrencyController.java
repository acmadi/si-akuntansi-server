package com.devteam.accounting.web.controller.rest;


import com.devteam.accounting.dto.CurrencyDto;
import com.devteam.accounting.dto.error.ErrorDto;
import com.devteam.accounting.dto.error.ValidationErrorDto;
import com.devteam.accounting.dto.validator.DtoValidator;
import com.devteam.accounting.service.CurrencyService;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.rest.params.BrowseParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.OptimisticLockException;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private DtoValidator validator;

    @RequestMapping(value = "/browse", method = RequestMethod.POST)
    public ResponseEntity<QueryResult> getCurrencies(@RequestBody BrowseParams params) {
        if (StringUtils.isEmpty(params.getKeyword())) {
            QueryResult result = currencyService.findAlls(params.getOrders(), params.getStart(), params.getCount());
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            String querySearch = String.format("%%%s%%", params.getKeyword());
            QueryResult result = currencyService.findByKeyword(params.getOrders(), params.getStart(),
                    params.getCount(), querySearch);
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getCurrency(@PathVariable("id") Long id) {
        CurrencyDto dto = currencyService.findById(id);
        return (dto != null) ?
                new ResponseEntity(dto, HttpStatus.OK) :
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody final CurrencyDto dto) {
        ValidationErrorDto errors = validator.validate(dto);
        if (errors.hasErrors()) {
            return new ResponseEntity(errors, HttpStatus.FORBIDDEN);
        }

        try {
            currencyService.save(dto);
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (OptimisticLockException ole) {
            return responseConflictError(ole);
        } catch (Exception e) {
            return responseSqlError(e);
        }
    }

    @RequestMapping(value = "/form", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final CurrencyDto dto) {
        ValidationErrorDto errors = validator.validate(dto);
        if (errors.hasErrors()) {
            return new ResponseEntity(errors, HttpStatus.FORBIDDEN);
        }

        try {
            currencyService.update(dto);
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (OptimisticLockException ole) {
            return responseConflictError(ole);
        } catch (Exception e) {
            return responseSqlError(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            currencyService.removeById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private ResponseEntity responseConflictError(Exception e) {
        ValidationErrorDto errors = new ValidationErrorDto();
        errors.addError(new ErrorDto(ErrorDto.MESSAGE_LOCKING_ERROR, e.getMessage()));
        return new ResponseEntity(errors, HttpStatus.CONFLICT);
    }

    private ResponseEntity responseSqlError(Exception e) {
        ValidationErrorDto errors = new ValidationErrorDto();
        errors.addError(new ErrorDto(ErrorDto.MESSAGE_SQL_ERROR, e.getMessage()));
        return new ResponseEntity(errors, HttpStatus.CONFLICT);
    }


}
