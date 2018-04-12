package com.git.integration.decode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.git.integration.domain.Commit;
import com.git.integration.domain.CommitFile;
import com.git.integration.exceptions.UnparseableCommitException;

/**
 * @see CommitDecoderTest
 */
public class CommitDecoder {
	
	public Commit decode(String string) throws UnparseableCommitException{
		try {
			string = string.replaceAll("\r", "");
			String hash = decodeHash(string);
			String branch = decodeBranch(string);
			String author = decodeAuthor(string);
			LocalDateTime time = decodeTime(string);
			
			String description = decodeDescription(string);
			if (description.length() >= 990) {
				description = description.substring(0, 985) + "...";
			}
			
			Commit commit = new Commit(hash, branch, author, time, description);
			
			List<CommitFile> files = decodeFiles(string);
			
			for (CommitFile commitFile : files) {
				commit.add(commitFile);
			}
			
			return commit;
			
		} catch (Exception e) {
			throw new UnparseableCommitException(e);
		}
	}
	
	public String decodeHash(String string) {
		return decodeRegex(string, "(?<=commit )\\w+");
	}
	
	public String decodeBranch(String string) {
		String regex = Pattern.quote("[") + "(.*?)" + Pattern.quote("]: ");
		return decodeRegex(string, regex, 1);
	}

	public String decodeAuthor(String string) {
		String regex = Pattern.quote("Author: ") + "(.*?)" + Pattern.quote(" <");
		return decodeRegex(string, regex, 1);
	}
	
	public LocalDateTime decodeTime(String string) {
		String timeAsString = decodeRegex(string.replaceAll("\\s+", " "), "(?<=Date: )\\d{4}-\\d{2}-\\d{2}-\\d{2}:\\d{2}:\\d{2}");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		return LocalDateTime.from(formatter.parse(timeAsString));
	}
	
	public String decodeDescription(String string) {
		StringBuilder result = new StringBuilder();
		String[] rows = string.split("\n");
		
		if (rows[4].indexOf("]: ") > 0) {
			rows[4] = rows[4].substring(rows[4].indexOf("]: ") + 3).trim();
		}
		
		for (int i = 4; i < rows.length; i++) {
			String row = rows[i].replaceAll("\\s+", " ").trim();
			if (row.length() > 0) {
				if (result.length() > 0) {
					result.append("\r\n");
				}
				result.append(row);
			}else {
				break;
			}
		}
		return result.toString();
	}
	
	public List<CommitFile> decodeFiles(String string) {
		String[] rows = string.split("\n");
		List<CommitFile> result = new ArrayList<>();
		for (int i = rows.length-1; i >= 0; i--) {
			String row = rows[i].replaceAll("\\s+", " ").trim();
			if (row.length() > 0) {
				CommitFile file = new CommitFile(row.substring(2), row.substring(0, 1));
				result.add(file);
			}else {
				break;
			}
		}
		return result;
	}
	
	private String decodeRegex(String string, String regex) {
		return decodeRegex(string, regex, 0);
	}
	
	private String decodeRegex(String string, String regex, int group) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()) {
			return matcher.group(group);
		}
		return null;
	}
	
}
