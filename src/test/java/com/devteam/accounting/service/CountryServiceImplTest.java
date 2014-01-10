package com.devteam.accounting.service;

import com.devteam.accounting.dto.CountryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: pancara
 * Date: 1/8/14
 * Time: 9:25 AM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/beans-test.xml"})
public class CountryServiceImplTest {
    @Autowired
    private CountryService countryService;

    @Test
    public void save() {
        CountryDto dto = new CountryDto();
        dto.setName("Indonesia");
        dto.setIso2("ID");
        dto.setIso3("IDN");

        countryService.save(dto);
    }
}
