package com.git.integration.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.git.integration.common.DateTimeUtils;

@Entity
@Table(name = "GIT_COMMITS")
public class Commit {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_GIT_COMMIT")
	@SequenceGenerator(name="GEN_GIT_COMMIT", sequenceName="SEQ_GIT_COMMIT", allocationSize = 1)
	private Long id;
	
	@Column(name="HASH", length=40)
	private String hash;
	
	@Column(name="REPOSITORY", length=100)
	private String repository;
	
	@Column(name="BRANCH", length=100)
	private String branch;
	
	@Column(name="AUTHOR", length=100)
	private String author;
	
	@Column(name="TIME")
	private LocalDateTime time;
	
	@Column(name="INTEGRATION_TIME")
	private LocalDateTime integrationTime;
	
	@Column(name="DESCRIPTION", length=1000)
	private String description;
	
	@OneToMany(mappedBy = "commit", targetEntity = CommitFile.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("FILE_NAME")
	private List<CommitFile> files;
	
	private String link;

	public Commit(String hash, String branch, String author, LocalDateTime time, String description) {
		this();
		this.hash = hash;
		this.branch = branch;
		this.author = author;
		this.time = time;
		this.description = description;
	}

	public Commit() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public LocalDateTime getIntegrationTime() {
		return integrationTime;
	}
	public void setIntegrationTime(LocalDateTime integrationTime) {
		this.integrationTime = integrationTime;
	}
	public String getIntegrationTimeAsString(Locale locale) {
		return DateTimeUtils.format(getIntegrationTime(), locale);
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<CommitFile> getFiles() {
		return files;
	}
	public void setFiles(List<CommitFile> files) {
		this.files = files;
	}
	public void add(CommitFile commitFile) {
		if(getFiles() == null) {
			setFiles(new ArrayList<>());
		}
		getFiles().add(commitFile);
		commitFile.setCommit(this);
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Commit [hash=");
		builder.append(hash);
		builder.append(", branch=");
		builder.append(branch);
		builder.append(", author=");
		builder.append(author);
		builder.append(", time=");
		builder.append(time);
		builder.append(", description=");
		builder.append(description);
		builder.append(", files=");
		builder.append(files);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
