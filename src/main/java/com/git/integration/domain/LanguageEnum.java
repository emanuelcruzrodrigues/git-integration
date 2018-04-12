package com.git.integration.domain;

public enum LanguageEnum {
	 PORTUGUES_BR("pt_BR", "Portugues - BR")
	,INGLES_US("en_US", "English - US")
	;
	private String value;
	private String meaning;
	
	private LanguageEnum(String value, String meaning) {
		this.value = value;
		this.meaning = meaning;
	}

	public String getValue() {
		return value;
	}

	public String getMeaning() {
		return meaning;
	}
	
	
	

}
