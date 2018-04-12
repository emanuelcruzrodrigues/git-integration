package com.git.integration.exceptions;

@SuppressWarnings("serial")
public class UnparseableCommitException extends Exception{

	public UnparseableCommitException(Throwable throwable) {
		super(throwable);
	}
	
}
