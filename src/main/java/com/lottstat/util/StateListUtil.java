package com.lottstat.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.lottstat.entity.State;

public class StateListUtil {
	public static List<State> getLotteryStates() {
		List<State> answer = new ArrayList<State>();
		answer.add(State.FLORIDA);
		answer.add(State.ILLINOIS);
		answer.add(State.WASHINGTON);
		
		return answer;
	}
	
	public static JSONObject getLotteryStatesJSON() {
		JSONObject b = new JSONObject();
		
		for (State state: getLotteryStates()) {
			b.put(state.getAbbreviation(), state.toString());
		}
		
		return b;
	}
}
