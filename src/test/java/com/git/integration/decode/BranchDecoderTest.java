package com.git.integration.decode;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.git.integration.decode.BranchDecoder;

public class BranchDecoderTest {
	
	private static final String EXAMPLE = "  99600\n" + 
										  "  99601\n" + 
										  "* master\n" + 
										  "";
	private BranchDecoder decoder;

	@Before
	public void setUp() throws Exception {
		decoder = new BranchDecoder();
	}

	@Test
	public void test_Decode() {
		List<String> branches = decoder.decode(EXAMPLE);
		
		assertEquals(3, branches.size());
		assertEquals("99600", branches.get(0));
		assertEquals("99601", branches.get(1));
		assertEquals("master", branches.get(2));
		
	}

}
