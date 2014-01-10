package com.devteam.accounting.dto.validator;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * User: pancara
 * Date: 1/8/14
 * Time: 6:08 PM
 */
public class DtoValidator {

    private Validator validator;

    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public Validator getValidator() {
        return this.getValidator();
    }

}
