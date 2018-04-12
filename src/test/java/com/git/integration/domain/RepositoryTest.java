package com.git.integration.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.git.integration.domain.Repository;

public class RepositoryTest {

	@Test
	public void test() {
		Repository repository = new Repository();
		repository.setFileName("/home/emanuel/git-log-test/server/Antara-Core-Client.git");
		assertEquals("server", repository.getGroup());
		assertEquals("Antara-Core-Client", repository.getRepositoryName());
	}

}
