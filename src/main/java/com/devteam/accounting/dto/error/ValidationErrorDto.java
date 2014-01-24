package com.devteam.accounting.dto.error;

import java.util.ArrayList;
import java.util.List;

/**
 * User: pancara
 * Date: 1/10/14
 * Time: 10:08 AM
 */
public class ValidationErrorDto {

    private List<ErrorDto> errors = new ArrayList<>();

    public ValidationErrorDto() {
    }

    public void addError(ErrorDto error) {
        errors.add(error);
    }

    public void addError(String message) {
        errors.add(new ErrorDto(message));
    }

    public void addFieldError(String field, String message) {
        ErrorFieldDto error = new ErrorFieldDto(field, message);
        errors.add(error);
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
