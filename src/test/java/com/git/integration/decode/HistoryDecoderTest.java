package com.git.integration.decode;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.git.integration.decode.HistoryDecoder;

public class HistoryDecoderTest {
	
	private static final String EXAMPLE = "Microsoft Windows [vers�o 10.0.16299.309]\r\n" + 
			"(c) 2017 Microsoft Corporation. Todos os direitos reservados.\r\n" + 
			"\r\n" + 
			"C:\\dev\\git\\git-integration>cd C:\\dev\\git-test\\my-repo\r\n" + 
			"\r\n" + 
			"C:\\dev\\git-test\\my-repo>git log --name-status --all --date=local --source --date=format:%Y-%m-%d-%H:%M:%S\r\n" + 
			"commit 1ea8f4319fa5e1361572d2ad646d35bff7058357	refs/heads/master\r\n" + 
			"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
			"Date:   2018-03-24-00:23:39\r\n" + 
			"\r\n" + 
			"    ultimo\r\n" + 
			"\r\n" + 
			"M	Novo Documento de Texto.txt\r\n" + 
			"\r\n" + 
			"commit e02fd9a36dc0b73f6c7e422422f0ca691dc78448	refs/heads/master\r\n" + 
			"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
			"Date:   2018-03-23-23:22:55\r\n" + 
			"\r\n" + 
			"    teste5\r\n" + 
			"\r\n" + 
			"M	Novo Documento de Texto.txt\r\n" + 
			"\r\n" + 
			"commit 72f9804ae3aec35b534da315328b79697c88964d	refs/heads/b1\r\n" + 
			"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
			"Date:   2018-03-23-23:21:53\r\n" + 
			"\r\n" + 
			"    teste4\r\n" + 
			"\r\n" + 
			"A	Novo Documento de Texto - Copia (2).txt\r\n" + 
			"\r\n" + 
			"commit bb83b755cc5f8299213e4f2350dd721e3b96d0ca	refs/heads/b1\r\n" + 
			"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
			"Date:   2018-03-23-23:20:37\r\n" + 
			"\r\n" + 
			"    teste3\r\n" + 
			"\r\n" + 
			"A	Novo Documento de Texto - Copia - Copia.txt\r\n" + 
			"\r\n" + 
			"commit b6b0bd705a3c2ad1d3c8eb57e8b52f0ba9945c3e	refs/heads/master\r\n" + 
			"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
			"Date:   2018-03-23-23:18:37\r\n" + 
			"\r\n" + 
			"    teste2\r\n" + 
			"\r\n" + 
			"A	Novo Documento de Texto - Copia.txt\r\n" + 
			"\r\n" + 
			"commit 33f1a2b555f2218cb37578020a855b2935132ce0	refs/heads/master\r\n" + 
			"Author: Emanuel Cruz Rodrigues <emanuelcruzrodrigues@gmail.com>\r\n" + 
			"Date:   2018-03-23-23:17:55\r\n" + 
			"\r\n" + 
			"    teste1\r\n" + 
			"\r\n" + 
			"A	Novo Documento de Texto.txt\r\n" + 
			"\r\n" + 
			"C:\\dev\\git-test\\my-repo>exit\r\n" + 
			"\r\n" + 
			"";

	private HistoryDecoder decoder;
	
	@Before
	public void setUp(){
		decoder = new HistoryDecoder();
	}

	@Test
	public void test() {
		List<String> commits = decoder.decode(EXAMPLE);
		assertEquals(6, commits.size());
	}

}
