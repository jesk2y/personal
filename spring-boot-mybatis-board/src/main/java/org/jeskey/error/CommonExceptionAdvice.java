package org.jeskey.error;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

	  //기타 에러

	  @ExceptionHandler(Exception.class) public String
	  handleAlltException(Exception e, Model model) {

		  log.error("cause: {}, message: {}",
		  NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());

		  model.addAttribute("errorMsg", e.getMessage()); return "/error/error.html";
	  }

	//잘못된 주소
	@ExceptionHandler(NoResourceFoundException.class)
	public String handle404Exception(Exception e, Model model) {

		log.error("cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());

		return "/error/404.html";
	}

	//잘못된 파라미터
	@ExceptionHandler({MethodArgumentTypeMismatchException.class
			, MissingServletRequestParameterException.class, IllegalArgumentException.class})
	public String handleParameterException(Exception e, Model model) {

		log.error("cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());

		return "/error/400.html";
	}

}