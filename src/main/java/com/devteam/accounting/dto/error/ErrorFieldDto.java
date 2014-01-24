package com.devteam.accounting.dto.error;

/**
 * User: pancara
 * Date: 1/10/14
 * Time: 10:08 AM
 */
public class ErrorFieldDto extends ErrorDto {

    private String field;

    public ErrorFieldDto() {
    }

    public ErrorFieldDto(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
