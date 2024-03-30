package com.BlogApp.exception;

import com.BlogApp.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final HttpStatus http = HttpStatus.NOT_FOUND;
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundHandle(ResourceNotFoundException userNotFoundException){
    ErrorResponse errorResponse = new ErrorResponse(userNotFoundException.getMessage(), http, LocalDateTime.now());
    return new ResponseEntity<>(errorResponse,http);
    }
@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgument(MethodArgumentNotValidException methodArgumentNotValidException
    ){
    Map<String,String> response = new HashMap<>();
    methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error)->{
        String fieldName = ((FieldError)error).getField();
        String message = error.getDefaultMessage();
        response.put(fieldName,message);
    });
return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
