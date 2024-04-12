package org.jeskey.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class RestExceptionAdvice {

	//댓글 - 무결성 위반
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<Map<String, String>> handleFKException(Exception e){

		log.error("cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());

		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMsg", "constraint fails");

		return ResponseEntity.badRequest().body(errorMap);
	}
}
