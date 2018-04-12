package com.git.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.git.integration.integration.GitCommitIntegration;

@RestController
public class IntegrationRestController {
	
	@Autowired
	private GitCommitIntegration gitCommitIntegration;
	
	@RequestMapping(value = "/integration/commit")
	public String actionRunCommitsIntegration(Model model) {
		new Thread(() -> gitCommitIntegration.run()).start();
		return "ok";
	}
	
}
