package com.tickettracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Pooja Mantri
 *
 */
@Controller
@RequestMapping(value={"/","/homepage"})
public class HomeController {

	@RequestMapping(method=RequestMethod.GET)
	public String getHomePage()
	{
		return "index";
	}
}
