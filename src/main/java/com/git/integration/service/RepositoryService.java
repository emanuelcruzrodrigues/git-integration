package com.git.integration.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.git.integration.domain.Repository;

@Service
public class RepositoryService {
	
	@Value("${repositories.path}")
	private String repositoriesPath;
	
	public List<Repository> getRepositories(){
		List<File> gitRepositories = new ArrayList<>(); 
		getGitRepositories(new File(repositoriesPath), gitRepositories);
		
		List<Repository> result = gitRepositories.stream().map(file -> new Repository(file)).collect(Collectors.toList());
		Collections.sort(result);
		return result;
	}
	
	private void getGitRepositories(File directory, List<File> result) {
		
		if (!directory.isDirectory()) return;
		
		for (File subDirectory : directory.listFiles()) {
			String name = subDirectory.getName();
			if (!name.endsWith(".wiki.git") && name.endsWith(".git")) {
				result.add(subDirectory);
			}else {
				getGitRepositories(subDirectory, result);
			}
		}
	}

	public Map<String, String> getRepositoriesGroupMap() {
		Map<String, String> result = getRepositories().stream().collect(Collectors.toMap(r -> r.getRepositoryName(), r -> r.getGroup()));
		return result;
	}
	
}
