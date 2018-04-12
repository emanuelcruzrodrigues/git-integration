package com.git.integration.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Configuration implements Serializable{
	
	private String language;

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

}
