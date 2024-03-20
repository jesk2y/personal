package org.jeskey.common;


import org.springframework.core.NestedExceptionUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException e, Model model){

		log.error("[NullPointerException] cause: {}, message: {}",
				  NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());

		model.addAttribute("errorMsg", e.getMessage());
		return "/error/400.html";
	}
}