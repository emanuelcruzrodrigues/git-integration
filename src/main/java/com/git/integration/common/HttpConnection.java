package com.git.integration.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HttpConnection {
	
	private final String USER_AGENT = "Mozilla/5.0";
	
	public String sendGet(String urlAddress) throws Exception {

		Logger logger = LogManager.getLogger(getClass());
		
		StringBuilder response = new StringBuilder();
		
		URL url = new URL(urlAddress);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = connection.getResponseCode();
		
		logger.info(String.format("Sending 'GET' request to URL: %s. Response Code: %d", urlAddress, responseCode));

		try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
		}

		return response.toString();

	}
	
	public String sendPost(String urlAddress, String urlParameters) throws Exception {
		
		Logger logger = LogManager.getLogger(getClass());
		
		StringBuilder response = new StringBuilder();

		URL url = new URL(urlAddress);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		connection.setDoOutput(true);
		
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.writeBytes(urlParameters);
		outputStream.flush();
		outputStream.close();

		int responseCode = connection.getResponseCode();
		logger.info(String.format("Sending 'POST' request to URL: %s. Response Code: %d", urlAddress, responseCode));

		try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
		}

		return response.toString();

	}


}
