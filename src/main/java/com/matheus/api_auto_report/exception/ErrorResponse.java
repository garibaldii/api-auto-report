package com.matheus.api_auto_report.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ErrorResponse {

    private int status;
    private String message;
    private Map<String, String> errors;

}
