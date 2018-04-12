package com.git.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.integration.service.LogService;

@RestController
public class LogRestController {
	
	@Autowired
	private LogService logService;

	@RequestMapping(value = "/rest/log")
	public String getLog(){
		return logService.getLog();
	}
	
}
