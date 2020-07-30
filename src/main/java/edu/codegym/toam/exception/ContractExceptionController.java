package edu.codegym.toam.exception;

import edu.codegym.toam.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContractExceptionController {
    @ExceptionHandler(value = ContractException.class)
    public ResponseEntity<Object> checkTimeException(ContractException exception) {
        return ResponseEntity.badRequest().body(new MessageResponse("Check in or check out time is not valid"));
    }
}
