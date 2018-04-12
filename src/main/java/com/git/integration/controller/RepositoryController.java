package com.git.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.integration.domain.Repository;
import com.git.integration.service.RepositoryService;

@Controller
public class RepositoryController {

	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping(value = "/repository/list")
	public String actionList(Model model) {
		List<Repository> repositories = repositoryService.getRepositories();
		model.addAttribute("repositories", repositories);
		return "repository_list";
	}
	
}
