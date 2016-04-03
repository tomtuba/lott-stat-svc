package com.lottstat.util;

import java.util.ArrayList;
import java.util.List;

import com.lottstat.entity.Game;
import com.lottstat.entity.Prize;
import com.lottstat.entity.State;

public class MockState {
	public static State getMockState(){
		State mockState = new State();
		mockState.setName("Mississippi");
		mockState.setAbbrev("MS");
		List<Game> games = new ArrayList<Game>();
		Game gameOne = new Game();
		List<Prize> prizes = new ArrayList<Prize>();
		Prize prizeOne = new Prize();
		prizeOne.setValue(10000000);
		prizeOne.setTotalPrizes(5);
		prizeOne.setRemainingPrizes(3);
		prizes.add(prizeOne);
		gameOne.setName("Mega Millions");
		gameOne.setGameCost(10);
		gameOne.setPrizes(prizes);
		games.add(gameOne);
		mockState.setGames(games);
		gameOne.setPrizes(prizes);
		
		
		
		return mockState;
	}

}
