package com.git.integration.decode;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.git.integration.decode.CommitDecoder;
import com.git.integration.domain.CommitFile;

public class CommitDecoderTest {

	private static final String EXAMPLE = "commit 1ea8f4319fa5e1361572d2ad646d35bff7058357	refs/heads/master\r\n" + 
											"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
											"Date:   2018-03-24-00:23:39\r\n" + 
											"\r\n" + 
											"    [99601]: some information about changes\r\n" + 
											"    second line of information\r\n" + 
											"\r\n" + 
											"M	First file.txt\r\n" +
											"A	New File.txt";
	
	private static final String EXAMPLE_2 = "commit 1ea8f4319fa5e1361572d2ad646d35bff7058357	refs/heads/master\r\n" + 
											"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
											"Date:   2018-03-24-00:23:39\r\n" + 
											"\r\n" + 
											"    [99601] some information about changes\r\n" + 
											"    second line of information\r\n" + 
											"\r\n" + 
											"M	First file.txt\r\n" +
											"A	New File.txt";
	
	private CommitDecoder decoder;
	
	@Before
	public void setUp() {
		decoder = new CommitDecoder();
	}
	
	@Test
	public void test_Decode_Hash() {
		String hash = decoder.decodeHash(EXAMPLE);
		assertEquals("1ea8f4319fa5e1361572d2ad646d35bff7058357", hash);
	}
	
	@Test
	public void test_Decode_Branch() {
		String branch = decoder.decodeBranch(EXAMPLE);
		assertEquals("99601", branch);
	}
	
	@Test
	public void test_Decode_Author() {
		String author = decoder.decodeAuthor(EXAMPLE);
		assertEquals("Emanuel Cruz Rodrigues", author);
	}
	
	@Test
	public void test_Decode_Time() {
		LocalDateTime time = decoder.decodeTime(EXAMPLE);
		assertEquals(LocalDateTime.of(2018, 3, 24, 0, 23, 39), time);
	}
	
	@Test
	public void test_Decode_Description() {
		String description = decoder.decodeDescription(EXAMPLE);
		assertEquals("some information about changes\r\nsecond line of information", description);
		
		String description2 = decoder.decodeDescription(EXAMPLE_2);
		assertEquals("[99601] some information about changes\r\nsecond line of information", description2);
	}
	
	@Test
	public void test_Decode_Files() {
		List<CommitFile> files = decoder.decodeFiles(EXAMPLE);
		
		assertEquals(2, files.size());
		
		assertEquals("A", files.get(0).getFileStatus());
		assertEquals("New File.txt", files.get(0).getFileName());
		
		assertEquals("M", files.get(1).getFileStatus());
		assertEquals("First file.txt", files.get(1).getFileName());
	}
	
	

}
