package roomescape.global;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import roomescape.global.dto.ApiFieldError;
import roomescape.global.dto.ApiInputErrorResult;
import roomescape.global.exception.ApiException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ApiInputErrorResult> handleMalformedInput(MethodArgumentNotValidException exception) {
		List<ApiFieldError> errors = exception.getBindingResult().getAllErrors()
				.stream().map(error -> {
					if(error instanceof FieldError fieldError) {
						return new ApiFieldError(fieldError.getField(),
								fieldError.getCode(),
								fieldError.getDefaultMessage());
					} else {
						return null;
					}
				})
				.filter(Objects::nonNull)
				.toList();
		
		ApiInputErrorResult result = new ApiInputErrorResult("MalformedInput", "입력 형식이 잘못되었습니다.", errors);
		
		return ResponseEntity.status(exception.getStatusCode())
				.body(result);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleMalformedInput(HttpMessageNotReadableException exception) {
		Throwable cause = exception.getCause();
		if(cause instanceof JsonMappingException jsonException) {
			String field = jsonException.getPath().stream()
					.map(JsonMappingException.Reference::getFieldName)
					.collect(Collectors.joining("."));
			
			return ResponseEntity.badRequest()
					.body(new ApiFieldError(field, jsonException.getClass().getSimpleName(), "JSON 파싱에 실패했습니다."));
		}
		
		return ResponseEntity.badRequest()
				.body(new ApiInputErrorResult(exception.getClass().getSimpleName(), exception.getMessage(), null));
	}
	
	@ExceptionHandler
	public ResponseEntity<? extends ApiException.Dto> handleApiException(ApiException exception) {
		return exception.getResponse();
	}
}
