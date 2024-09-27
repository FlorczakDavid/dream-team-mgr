package co.simplon.dreamteam.mgr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LanguageTechnologyInvalidNameException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public LanguageTechnologyInvalidNameException(String message) {
        super(message);
    }
}
