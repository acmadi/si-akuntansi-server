package com.devteam.accounting.web.controller.rest;


import com.devteam.accounting.dto.AccountDto;
import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.service.CountryService;
import com.devteam.accounting.service.wrapper.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<QueryResult> getCountries() {
        QueryResult result = countryService.findAlls();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable("id") Long id) {
        CountryDto dto = countryService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody final CountryDto dto) {
        countryService.save(dto);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final CountryDto dto) {
        countryService.update(dto);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        countryService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
