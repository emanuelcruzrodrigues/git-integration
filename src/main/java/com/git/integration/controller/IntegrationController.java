package com.git.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.integration.service.LogService;

@Controller
public class IntegrationController {
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value = "/integration")
	public String actionShowIntegrations(Model model) {
		model.addAttribute("log", logService.getLog());
		return "integration";
	}
	
}
