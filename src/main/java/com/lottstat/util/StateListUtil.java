package com.lottstat.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.lottstat.entity.StateEnum;

public class StateListUtil {
	public static List<StateEnum> getLotteryStates() {
		List<StateEnum> answer = new ArrayList<StateEnum>();
		answer.add(StateEnum.FLORIDA);
		answer.add(StateEnum.ILLINOIS);
		answer.add(StateEnum.WASHINGTON);
		
		return answer;
	}
	
	public static JSONObject getLotteryStatesJSON() {
		JSONObject b = new JSONObject();
		
		for (StateEnum state: getLotteryStates()) {
			b.put(state.getAbbreviation(), state.toString());
		}
		
		return b;
	}
}
