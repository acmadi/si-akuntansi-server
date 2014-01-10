package com.devteam.accounting.dto.error;

import java.util.ArrayList;
import java.util.List;

/**
 * User: pancara
 * Date: 1/10/14
 * Time: 10:08 AM
 */
public class ValidationErrorDto {

    private List<FieldErrorDto> errors = new ArrayList<>();

    public ValidationErrorDto() {
    }

    public void addFieldError(String field, String message) {
        FieldErrorDto error = new FieldErrorDto(field, message);
        errors.add(error);
    }

    public List<FieldErrorDto> getErrors() {
        return errors;
    }
}
