package com.clinic.library.exception;

public class ClinicException extends RuntimeException {
    private String code;
    public ClinicException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
