package com.lottstat.entity;

import java.util.List;

public class Game {
	
	private String name;
	private String gameNumber;
	private int gameCost;
	private List<Prize> prizes;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(String gameNumber) {
		this.gameNumber = gameNumber;
	}
	public int getGameCost() {
		return gameCost;
	}
	public void setGameCost(int gameCost) {
		this.gameCost = gameCost;
	}
	public List<Prize> getPrizes() {
		return prizes;
	}
	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}
	
	
	
}
