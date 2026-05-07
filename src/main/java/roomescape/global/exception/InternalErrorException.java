package roomescape.global.exception;

import org.springframework.http.ResponseEntity;

public class InternalErrorException extends ApiException {
	public InternalErrorException(Exception cause) {
		super("내부 오류가 발생했습니다. 개발자에게 문의해주세요. 오류 코드: " + cause.getCause().getMessage());
	}
	
	@Override
	public ResponseEntity<Dto> getResponse() {
		return ResponseEntity.internalServerError().body(new Dto());
	}
	
	
	public class Dto extends ApiException.Dto {
		@Override
		public String getType() {
			return "InternalError";
		}
	}
}
