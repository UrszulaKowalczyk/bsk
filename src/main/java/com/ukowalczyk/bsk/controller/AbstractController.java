package com.ukowalczyk.bsk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AbstractController {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		log.error(ex.getMessage(), ex);
		return "redirect:/";
	}

}
