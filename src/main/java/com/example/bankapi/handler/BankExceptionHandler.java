package com.example.bankapi.handler;

import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.NotEqualAccountIdException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BankExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchAccountException.class)
    protected ResponseEntity<noSuchAccountException> handleThereIsNoSuchAccountException() {
        return new ResponseEntity<>(new noSuchAccountException("There is no such account"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEqualAccountIdException.class)
    protected ResponseEntity<notEqualAccountIdException> handleNotEqualAccountIdException() {
        return new ResponseEntity<>(new notEqualAccountIdException("Url account id doesn't match with json account id"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyAccountException.class)
    protected ResponseEntity<emptyAccountException> handleEmptyAccountException() {
        return new ResponseEntity<>(new emptyAccountException("No cards"),
                HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    private static class noSuchAccountException {
        private String message;
    }

    @Data
    @AllArgsConstructor
    private static class notEqualAccountIdException {
        private String message;
    }

    @Data
    @AllArgsConstructor
    private static class emptyAccountException {
        private String message;
    }
}
