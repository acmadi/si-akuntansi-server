package com.devteam.accounting.web.controller.rest;


import com.devteam.accounting.dto.FiscalPeriodDto;
import com.devteam.accounting.dto.error.ErrorDto;
import com.devteam.accounting.dto.error.ValidationErrorDto;
import com.devteam.accounting.dto.validator.DtoValidator;
import com.devteam.accounting.service.FiscalPeriodService;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.rest.params.BrowseParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;

@Controller
@RequestMapping("/fiscal-period")
public class FiscalPeriodController {

    @Autowired
    private FiscalPeriodService fiscalPeriodService;

    @Autowired
    private DtoValidator validator;

    @RequestMapping(value = "/browse", method = RequestMethod.POST)
    public ResponseEntity<QueryResult> getCountries(@RequestBody BrowseParams params) {
        QueryResult result = fiscalPeriodService.findAlls(params.getOrders(), params.getStart(), params.getCount());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getCountry(@PathVariable("id") Long id) {
        FiscalPeriodDto dto = fiscalPeriodService.findById(id);
        return (dto != null) ?
                new ResponseEntity(dto, HttpStatus.OK) :
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody final FiscalPeriodDto dto) {
        ValidationErrorDto errors = validator.validate(dto);
        if (errors.hasErrors()) {
            return new ResponseEntity(errors, HttpStatus.FORBIDDEN);
        }

        try {
            fiscalPeriodService.save(dto);
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (OptimisticLockException ole) {
            return responseConflictError(ole);
        } catch (Exception e) {
            return responseSqlError(e);
        }
    }

    @RequestMapping(value = "/form", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final FiscalPeriodDto dto) {
        ValidationErrorDto errors = validator.validate(dto);
        if (errors.hasErrors()) {
            return new ResponseEntity(errors, HttpStatus.FORBIDDEN);
        }

        try {
            fiscalPeriodService.update(dto);
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
            fiscalPeriodService.removeById(id);
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
        errors.addError(new ErrorDto(ErrorDto.MESSAGE_SQL_ERROR, e.getCause().getMessage()));
        return new ResponseEntity(errors, HttpStatus.CONFLICT);
    }


}
