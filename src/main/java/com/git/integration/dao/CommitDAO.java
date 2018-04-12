package com.git.integration.dao;

import java.io.File;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.git.integration.domain.Commit;
import com.git.integration.domain.CommitFilter;

@Repository
public class CommitDAO {
	
	@Autowired
	private DAOManager dao;

	public boolean commitExists(Commit commit) {
		Map<String, Object> parameters = new HashMap<>();
		
		String hql = "select comm.id from Commit comm where comm.hash = :h and comm.repository = :r";
		parameters.put("h", commit.getHash());
		parameters.put("r", commit.getRepository());
		
		Long id = dao.uniqueResult(hql, parameters);
		return id != null;
	}

	public void save(Commit commit) {
		dao.save(commit);
	}

	public String getLastHash(File repository) {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<>();
		
		hql.append("select comm.hash from Commit comm ");
		hql.append(" where comm.id in ( ");
		hql.append(" 	select max(c.id) from Commit c where c.repository = :repository ");
		hql.append(" ) ");
		
		parameters.put("repository", repository.getName());
		
		String result = dao.uniqueResult(hql, parameters);
		return result;
	}

	public List<Commit> query(CommitFilter filter) {
		Map<String, Object> parameters = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select comm from Commit comm ");
		hql.append(" left join comm.files cofi ");
		hql.append(" where 1=1 ");
		
		if (StringUtils.isNotBlank(filter.getRepository())) {
			hql.append(" and upper(comm.repository) like :repository ");
			HQLUtil.addParameterLike(parameters, "repository", filter.getRepository().toUpperCase());
		}
		
		if (StringUtils.isNotBlank(filter.getBranch())) {
			hql.append(" and upper(comm.branch) like :branch ");
			HQLUtil.addParameterLike(parameters, "branch", filter.getBranch().toUpperCase());
		}
		
		if (StringUtils.isNotBlank(filter.getAuthor())) {
			hql.append(" and upper(comm.author) like :author ");
			HQLUtil.addParameterLike(parameters, "author", filter.getAuthor().toUpperCase());
		}
		
		if (StringUtils.isNotBlank(filter.getDescription())) {
			hql.append(" and upper(comm.description) like :description ");
			HQLUtil.addParameterLike(parameters, "description", filter.getDescription().toUpperCase());
		}
		
		if (StringUtils.isNotBlank(filter.getFileName())) {
			hql.append(" and upper(cofi.fileName) like :fileName ");
			HQLUtil.addParameterLike(parameters, "fileName", filter.getFileName().toUpperCase());
		}
		
		if (filter.getStartDate() != null) {
			hql.append(" and comm.integrationTime >= :startTime ");
			parameters.put("startTime", filter.getStartDate().atTime(LocalTime.MIN));
		}
		
		if (filter.getFinalDate() != null) {
			hql.append(" and comm.integrationTime <= :endTime ");
			parameters.put("endTime", filter.getFinalDate().plusDays(1).atTime(LocalTime.MIN).minusSeconds(1));
		}
		
		hql.append(" order by comm.id desc, cofi.fileName ");
		
		List<Commit> result = dao.query(hql, parameters, filter.getLimit());
		return result;
	}

	public List<Commit> queryDistinctBranches(CommitFilter example) {
		Map<String, Object> parameters = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		
		hql.append(" select distinct ");
		hql.append("   comm.repository as repository ");
		hql.append(" , comm.branch as branch ");
		hql.append(" from Commit comm ");
		hql.append(" left join comm.files cofi ");
		hql.append(" where 1=1 ");
		
		if (StringUtils.isNotBlank(example.getRepository())) {
			hql.append(" and upper(comm.repository) like :repository ");
			HQLUtil.addParameterLike(parameters, "repository", example.getRepository().toUpperCase());
		}
		
		if (StringUtils.isNotBlank(example.getBranch())) {
			hql.append(" and upper(comm.branch) like :branch ");
			HQLUtil.addParameterLike(parameters, "branch", example.getBranch().toUpperCase());
		}
		
		hql.append(" order by comm.repository, comm.branch ");
		
		List<Commit> result = dao.query(Commit.class, hql, parameters, example.getLimit());
		return result;
	}
	

}
