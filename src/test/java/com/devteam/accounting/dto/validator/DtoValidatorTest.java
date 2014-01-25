package com.devteam.accounting.dto.validator;

import com.devteam.accounting.dto.CountryDto;
import com.devteam.accounting.dto.FiscalPeriodDto;
import com.devteam.accounting.dto.error.ErrorDto;
import com.devteam.accounting.dto.error.ErrorFieldDto;
import com.devteam.accounting.dto.error.ValidationErrorDto;
import com.devteam.accounting.persistence.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: pancara
 * Date: 1/22/14
 * Time: 9:17 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/validator.xml"})
public class DtoValidatorTest {

    @Autowired
    private DtoValidator validator;

    @Test
    public void testValidateCountry() throws Exception {
        CountryDto dto = new CountryDto();
        dto.setIso2("X");
        dto.setIso3("XX");
        ValidationErrorDto errors = validator.validate(dto);

        for (ErrorDto error : errors.getErrors()) {
            ErrorFieldDto errorField = (ErrorFieldDto) error;
            System.out.println(errorField.getField() + " " + error.getMessage());
        }

    }

    @Test
    public void testValidateFiscalPeriod() throws Exception {
        FiscalPeriodDto dto = new FiscalPeriodDto();
        ValidationErrorDto errors = validator.validate(dto);

        for (ErrorDto error : errors.getErrors()) {
            ErrorFieldDto errorField = (ErrorFieldDto) error;
            System.out.println(errorField.getField() + " " + error.getMessage());
        }

    }
}
