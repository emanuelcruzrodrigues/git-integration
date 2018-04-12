package com.git.integration.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GIT_COMMIT_FILES")
@SuppressWarnings("serial")
public class CommitFile implements Serializable{
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GEN_GIT_COMMIT_FILES")
	@SequenceGenerator(name="GEN_GIT_COMMIT_FILES", sequenceName="SEQ_GIT_COMMIT", allocationSize = 1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ID_GIT_COMMIT", foreignKey=@ForeignKey(name = "FK_GIT_COMMIT_GIT_FILE"))
	private Commit commit;
	
	@Column(name="FILE_NAME", length=300)
	private String fileName;
	
	@Column(name="FILE_STATUS", length=1)
	private String fileStatus;
	
	public CommitFile(String fileName, String fileStatus) {
		this();
		this.fileName = fileName;
		this.fileStatus = fileStatus;
	}
	
	public CommitFile() {}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommitFile [fileName=");
		builder.append(fileName);
		builder.append(", fileStatus=");
		builder.append(fileStatus);
		builder.append("]");
		return builder.toString();
	}

	
	
}
