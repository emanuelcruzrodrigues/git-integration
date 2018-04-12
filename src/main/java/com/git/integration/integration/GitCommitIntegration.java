package com.git.integration.integration;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.git.integration.common.CommandLine;
import com.git.integration.decode.CommitDecoder;
import com.git.integration.decode.HistoryDecoder;
import com.git.integration.domain.Commit;
import com.git.integration.domain.Repository;
import com.git.integration.service.CommitService;
import com.git.integration.service.RepositoryService;

@Component
public class GitCommitIntegration extends AbstractIntegration {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private CommitService commitService;
	
	@Override
	void runIntegration() {
		List<Repository> repositories = repositoryService.getRepositories();
		if (repositories.size() == 0) {
			LogManager.getLogger(getClass()).info("No repositories found");
		}
		
		for (Repository repository : repositories) {
			List<Commit> commits = getCommits(repository.getFile());
			commitService.integrateNewCommits(commits);
		}
	}

	
	private List<Commit> getCommits(File repository){
		
		Logger logger = LogManager.getLogger(getClass());
		
		String cmdGoToRepository = String.format("cd %s", repository.getAbsolutePath());
		
		String cmdGetRepositoryHistory = "git --no-pager log --name-status --all --reverse --no-merges --date=local --date=format:%Y-%m-%d-%H:%M:%S --since=\"last month\"";
		
		String history = new CommandLine().run(cmdGoToRepository, cmdGetRepositoryHistory);
		if (StringUtils.isBlank(history)) return Collections.emptyList();
		
		List<String> commitsAsString = new HistoryDecoder().decode(history);
		
		CommitDecoder commitDecoder = new CommitDecoder();
		
		List<Commit> commits = new ArrayList<>();
		
		for (String commitAsString : commitsAsString) {
			try {
				Commit commit = commitDecoder.decode(commitAsString);
				commit.setRepository(repository.getName());
				commit.setIntegrationTime(LocalDateTime.now());
				commits.add(commit);
			} catch (Exception e) {}
		}
		
		logger.info(String.format("%d commits found in the repository %s in the last month", commits.size(), repository.getName()));
		
		return commits;

	}

}
