package com.clinic.library.dto;

public record ApiError(String code, String message, Integer status) {
}
