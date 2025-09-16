package com.matheus.api_auto_report.exception;


import com.matheus.api_auto_report.exception.exs.DuplicateData;
import com.matheus.api_auto_report.exception.exs.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();

                    fieldErrors.put(fieldName, errorMessage);
                });


        return new ErrorResponse(400, "Validation Failed", fieldErrors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) //404
    @ExceptionHandler(NotFound.class)
    public ErrorResponse handleNotFound(NotFound ex) {

       return new ErrorResponse(404, ex.getMessage(), null);
    }



    @ResponseStatus(HttpStatus.CONFLICT) // 409
    @ExceptionHandler(DuplicateData.class)
    public ErrorResponse handleEmailExists(DuplicateData ex) {
        return new ErrorResponse(409, ex.getMessage(), null);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServerError(Exception ex) {

        return new ErrorResponse(500, ex.getMessage(), null);
    }

}
