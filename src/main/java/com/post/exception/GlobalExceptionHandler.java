package com.post.exception;

import com.post.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails>handleResourceNotFoundException(ResourceNotFound ex){
        ErrorDetails erd = new ErrorDetails(ex.getMessage(),new Date());
    return new ResponseEntity<>(erd, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails>handleException(Exception ex){
        ErrorDetails erd = new ErrorDetails(ex.getMessage(),new Date());
        return new ResponseEntity<>(erd,HttpStatus.EXPECTATION_FAILED);
    }

}
