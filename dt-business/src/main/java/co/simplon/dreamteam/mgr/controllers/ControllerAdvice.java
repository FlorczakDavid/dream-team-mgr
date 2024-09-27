package co.simplon.dreamteam.mgr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.simplon.dreamteam.mgr.exceptions.LanguageTechnologyInvalidNameException;

public class ControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(LanguageTechnologyInvalidNameException.class)
    public ResponseEntity<String> handleInvalidLanguageTechnologyNameException(LanguageTechnologyInvalidNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
