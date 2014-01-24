package com.devteam.accounting.dto.error;

/**
 * User: pancara
 * Date: 1/19/14
 * Time: 12:25 PM
 */
public class ErrorDto {
    public static final String MESSAGE_LOCKING_ERROR = "record gagal dikunci.";
    public static final String MESSAGE_SQL_ERROR = "terjadi kegagalan operasi database.";

    private String message;
    private String detail;


    public ErrorDto() {
    }

    public ErrorDto(String message) {
        this.message = message;
    }

    public ErrorDto(String message, String detail) {
        this.message = message;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
