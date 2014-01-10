package com.devteam.accounting.dto;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * User: pancara
 * Date: 1/7/14
 * Time: 8:55 PM
 */
public class CountryDtoTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        AccountDto acc = new AccountDto();
        Set<ConstraintViolation<AccountDto>> violations = validator.validate(acc);

        acc.setCode("A");
        violations = validator.validateProperty(acc, "code");
        for(ConstraintViolation v : violations) {
            System.out.println(v.getMessage());
        }

        acc.setCode("C001");
        violations = validator.validate(acc);
        assertEquals(2, violations.size());

        acc.setName("C");
        violations = validator.validate(acc);
        assertEquals(1, violations.size());
    }


}
