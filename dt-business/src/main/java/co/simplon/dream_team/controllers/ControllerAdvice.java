package co.simplon.dream_team.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.simplon.dream_team.exceptions.InvalidLanguageTechnologyNameException;

public class ControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidLanguageTechnologyNameException.class)
    public ResponseEntity<String> handleInvalidLanguageTechnologyNameException(InvalidLanguageTechnologyNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
