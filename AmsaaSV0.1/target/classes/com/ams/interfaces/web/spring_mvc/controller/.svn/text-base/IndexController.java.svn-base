package com.ams.interfaces.web.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
	@RequestMapping(value = "login.action")
	public String loginPage(Model model)
	{
		return "login";
	}

	@RequestMapping("index.action")
	public String loadHomePage(Model model)
	{
		return "index";
	}
}
