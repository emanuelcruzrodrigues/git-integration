package com.git.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.git.integration.domain.Commit;
import com.git.integration.domain.CommitFilter;
import com.git.integration.service.CommitService;

@Controller
public class CommitController {

	@Autowired
	private CommitService commitService;
	
	@Value("${default.queries.limit}")
	private int defaultQueriesLimit;
	
	@RequestMapping(value = "/commit/list")
	public String actionList(Model model, CommitFilter example) {
		if (example.getLimit() == null) {
			example.setLimit(defaultQueriesLimit);
		}
		List<Commit> commits = commitService.getCommits(example);
		model.addAttribute("filter", example);
		model.addAttribute("commits", commits);
		return "commit_list";
	}
	
}
