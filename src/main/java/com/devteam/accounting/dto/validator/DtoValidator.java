package com.devteam.accounting.dto.validator;

import com.devteam.accounting.dto.error.ValidationErrorDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

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

    public <T> ValidationErrorDto validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(object, groups);

        ValidationErrorDto errors = new ValidationErrorDto();
        for (ConstraintViolation v : violations) {
            errors.addFieldError(v.getPropertyPath().toString(), v.getMessage());
        }
        return errors;
    }


    public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return validator.validateProperty(object, propertyName, groups);
    }


    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return validator.validateValue(beanType, propertyName, value, groups);
    }


}
