package com.devteam.accounting.web.controller.rest.lookup;


import com.devteam.accounting.service.CountryService;
import com.devteam.accounting.service.helper.OrderDir;
import com.devteam.accounting.service.wrapper.QueryResult;
import com.devteam.accounting.web.controller.rest.params.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/lookup")
public class LookupController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public ResponseEntity<QueryResult> getCountries() {

        List<Order> orders = new LinkedList<>();
        orders.add(new Order("name", OrderDir.ASC));

        QueryResult result = countryService.findAlls(orders);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
