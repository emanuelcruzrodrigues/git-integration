package com.git.integration.domain;

import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Repository implements Serializable, Comparable<Repository>{
	
	private transient File file;
	private String fileName;
	
	public Repository(File file) {
		this();
		this.file = file;
		this.fileName = file.getAbsolutePath();
	}

	public Repository() {
		super();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int compareTo(Repository o) {
		return getFileName().compareTo(o.getFileName());
	}

	public String getGroup() {
		try {
			String[] split = getFileName().replaceAll("\\\\", "/").split("/");
			return split[split.length-2];
		} catch (Exception e) {
			return null;
		}
	}

	public String getRepositoryName() {
		try {
			String[] split = getFileName().replaceAll("\\\\", "/").split("/");
			return split[split.length-1].replace(".git", "");
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
