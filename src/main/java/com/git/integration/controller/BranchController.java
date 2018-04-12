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
public class BranchController {
	
	@Autowired
	private CommitService commitService;
	
	@Value("${default.queries.limit}")
	private int defaultQueriesLimit;
	
	@RequestMapping(value = "/branch/list")
	public String actionList(Model model, CommitFilter example) {
		if (example.getLimit() == null) {
			example.setLimit(defaultQueriesLimit);
		}
		List<Commit> branches = commitService.getBranches(example);
		model.addAttribute("filter", example);
		model.addAttribute("branches", branches);
		return "branch_list";
	}

}
