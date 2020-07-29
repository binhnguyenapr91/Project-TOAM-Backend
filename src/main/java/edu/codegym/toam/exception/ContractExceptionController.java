package edu.codegym.toam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContractExceptionController {
    @ExceptionHandler(value = ContractException.class)
    public ResponseEntity<Object> checkInTimeException(ContractException exception) {
        return new ResponseEntity<>("Check in time is not valid", HttpStatus.NOT_ACCEPTABLE);
    }
}
