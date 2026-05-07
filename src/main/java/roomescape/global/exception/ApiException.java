package roomescape.global.exception;

import org.springframework.http.ResponseEntity;

public abstract class ApiException extends Exception {
	public ApiException(String message) {
		super(message);
	}
	
	public abstract ResponseEntity<? extends Dto> getResponse();
	
	
	public abstract class Dto {
		public abstract String getType();
		
		public String getMessage() {
			return ApiException.this.getMessage();
		}
	}
}
