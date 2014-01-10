package com.devteam.accounting.dto.error;

/**
 * User: pancara
 * Date: 1/10/14
 * Time: 10:08 AM
 */
public class FieldErrorDto {

    private String field;
    private String message;

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
