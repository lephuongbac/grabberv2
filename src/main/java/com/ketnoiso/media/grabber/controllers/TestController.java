package com.ketnoiso.media.grabber.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class TestController.
 */
@Controller
public class TestController {
	
	/**
	 * Execute home.
	 * 
	 * @return the string
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String executeHome() {
		return "aaa";
	}
}
