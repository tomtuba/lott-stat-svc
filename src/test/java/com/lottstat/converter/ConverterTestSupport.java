package com.lottstat.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ConverterTestSupport {
	public String getTestHtml(String stateAbbrev) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		String fileName = "test." + stateAbbrev + ".html";
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(
				    this.getClass()
				    .getClassLoader()
				    .getResourceAsStream(
				           fileName)));
			
			for (String inLine = br.readLine(); inLine != null; inLine = br.readLine()) {
				pw.println(inLine);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					
				}
			}
			pw.close();
		}
		
		return sw.toString();
	}
}
