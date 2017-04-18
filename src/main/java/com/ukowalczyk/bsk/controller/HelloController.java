package com.ukowalczyk.bsk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
public class HelloController {

	//@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printHello(Model model) {
		model.addAttribute("message", "siema");
		return "index";
	}

}