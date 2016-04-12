package com.lottstat.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lottstat.entity.Game;
import com.lottstat.entity.Prize;
import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;

public class FloridaConverter {
	public State convertState(String html) {
		State state = new State();
		state.setAbbrev(StateEnum.FLORIDA.getAbbreviation());
		state.setName(StateEnum.FLORIDA.toString());

		// This map is to keep track of which games have already been added
		Map<String, Game> gameMap = new HashMap<String, Game>();
		
		// This is the resulting list of games
		List<Game> games = new ArrayList<Game>();
		state.setGames(games);
		
		// Parse the html from the web page into a Jsoup Document
		Document doc = Jsoup.parse(html);
		
		// Get the rows from the prizesRemaining table
		Elements rows = doc.select(".prizesRemaining tbody tr");
		for (Element row : rows) {
			// Get all the cells from the row
			Elements cells = row.select("td");
			
			// The game name cell
			Element cellTwo = cells.get(1).select("a").get(0);
			String gameName = StringUtils.trim(cellTwo.html());

			// Find the game (if we already had it) or create it (if we didn't have it yet)
			Game game = gameMap.get(gameName);
			if (game == null) {
				game = new Game();
				game.setPrizes(new ArrayList<Prize>());
				game.setName(gameName);
				game.setGameCost(convertNumber(cells.get(4).html()));
				
				// Keep track of the new game so we don't create another one
				gameMap.put(gameName, game);
				games.add(game);
			}
			
			game.getPrizes().add(getPrize(cells));
		}

		return state;
	}
	
	private Prize getPrize(Elements cells) {
		Prize prize = new Prize();
				prize.setValue(convertNumber(cells.get(2).html()));
				prize.setRemainingPrizes(convertRemainingPrizes(cells.get(3).html()));
				prize.setTotalPrizes(convertTotalPrizes(cells.get(3).html()));
		return prize;
	}
	private int convertNumber(String html){
		// $250,000.00
		String dollars = StringUtils.substringBefore(html, ".");
		// $250,000
		String noComma = StringUtils.remove(dollars, ",");
		// $250000
		String dollarSign = StringUtils.substringAfter(noComma, "$");
		// 250000
		String noWords = StringUtils.substringBefore(dollarSign, " ");
		String noSlash = StringUtils.substringBefore(noWords, "/");
		return Integer.parseInt(noSlash);
	}
	private int convertRemainingPrizes(String html){
		//1 of 6 
		String remainingPrizes = StringUtils.substringBefore(html, " of");
		return Integer.parseInt(remainingPrizes);
	}
	private int convertTotalPrizes(String html){
		String totalPrizes = StringUtils.substringAfter(html, "of ");
		String noSpace = StringUtils.trim(totalPrizes);
		String noStar = StringUtils.remove(noSpace, "*");
		return Integer.parseInt(noStar);
	}
}
