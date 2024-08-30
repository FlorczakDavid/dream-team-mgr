package co.simplon.dream_team.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidLanguageTechnologyNameException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public InvalidLanguageTechnologyNameException(String message) {
        super(message);
    }
}
