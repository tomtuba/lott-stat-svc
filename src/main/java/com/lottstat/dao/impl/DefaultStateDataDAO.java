package com.lottstat.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.lottstat.dao.StateDataDAO;

public class DefaultStateDataDAO implements StateDataDAO {

	@Override
	public String getStateData(String url) {
		StringBuilder sb = new StringBuilder();
		try {
			URL stateURL = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(stateURL.openStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null){
				sb.append(inputLine);
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		return sb.toString();
	}

}
