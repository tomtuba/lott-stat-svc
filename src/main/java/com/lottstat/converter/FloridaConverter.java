package com.lottstat.converter;

import static org.apache.commons.lang3.StringUtils.remove;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.StringUtils.trim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lottstat.entity.Game;
import com.lottstat.entity.Prize;
import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;

public class FloridaConverter extends Converter {
	protected FloridaConverter() {
	}
	@Override
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
			String gameName = trim(cellTwo.html());

			// Find the game (if we already had it) or create it (if we didn't have it yet)
			Game game = gameMap.get(gameName);
			if (game == null) {
				game = new Game();
				game.setPrizes(new ArrayList<Prize>());
				game.setName(gameName);
				game.setGameCost(convertNumber(cells.get(4).html()));
				game.setGameNumber(trim(cells.get(0).html()));
				
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
	
	private int convertRemainingPrizes(String html){
		//1 of 6 
		String remainingPrizes = substringBefore(html, " of");
		return Integer.parseInt(remainingPrizes);
	}
	private int convertTotalPrizes(String html){
		String totalPrizes = substringAfter(html, "of ");
		String noSpace = trim(totalPrizes);
		String noStar = remove(noSpace, "*");
		return Integer.parseInt(noStar);
	}
	@Override
	public String getURL() {
		return "http://www.flalottery.com/remainingPrizes";
	}
}
