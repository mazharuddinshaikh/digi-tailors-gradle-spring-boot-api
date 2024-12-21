/**
 * 
 */
package in.tailor.digi.user.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in.tailor.digi.model.DtsApiResponse;
import in.tailor.digi.model.DtsException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	@ExceptionHandler(value = DtsException.class)
	public ResponseEntity<DtsApiResponse<String>> getDtsException(DtsException dtsException) {
		return ResponseEntity.status(dtsException.getResponse().getHttpStatus()).body(dtsException.getResponse());
	}
}
