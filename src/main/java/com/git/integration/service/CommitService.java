package com.git.integration.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.git.integration.dao.CommitDAO;
import com.git.integration.domain.Commit;
import com.git.integration.domain.CommitFilter;

@Service
public class CommitService {
	
	@Autowired
	private CommitDAO commitDAO;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Value("${gitlab.url}")
	private String gitlabURL;
	
	@Transactional
	public void integrateNewCommits(List<Commit> commits) {
		for (Commit commit : commits) {
			if (commitDAO.commitExists(commit)) continue;
			commitDAO.save(commit);
		}
	}

	public List<Commit> getCommits(CommitFilter example) {
		List<Commit> commits = commitDAO.query(example);
		
		Map<String, String> groupByRepository = repositoryService.getRepositoriesGroupMap();
		
		for (Commit commit : commits) {
			String repositoryName = commit.getRepository().replace(".git", "");
			commit.setLink(String.format("%s/%s/%s/commit/%s", gitlabURL, groupByRepository.get(repositoryName), repositoryName, commit.getHash()));
		}
		
		return commits;
	}

	public List<Commit> getBranches(CommitFilter example) {
		if (StringUtils.isBlank(example.getRepository()) && StringUtils.isBlank(example.getBranch())) return Collections.emptyList();
		return commitDAO.queryDistinctBranches(example);
	}

}
