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

public class DelawareConverter extends Converter {
	protected DelawareConverter() {
	}
	@Override
	public State convertState(String html) {
		State state = new State();
		state.setAbbrev(StateEnum.DELAWARE.getAbbreviation());
		state.setName(StateEnum.DELAWARE.toString());

		// This map is to keep track of which games have already been added
		Map<String, Game> gameMap = new HashMap<String, Game>();
		
		// This is the resulting list of games
		List<Game> games = new ArrayList<Game>();
		state.setGames(games);
		
		// Parse the html from the web page into a Jsoup Document
		Document doc = Jsoup.parse(html);
		
		// Get the rows from the prizesRemaining table
		Elements rows = doc.select("tbody tr td tbody tr:not(:first-child)");
		int gameCost = 0;
		for (Element row : rows) {
			// Get all the cells from the row
			Elements cells = row.select("td");
			int cellCount = cells.size();
			if(cellCount == 1){
				//get game cost
				
				gameCost = convertNumber(cells.get(0).select("a").get(0).html());
				
			}
			else if(cellCount == 4){
				//get ticket info
				// The game name cell
				Element cellTwo = cells.get(1);
				String gameName = StringUtils.trim(cellTwo.html());

				// Find the game (if we already had it) or create it (if we didn't have it yet)
				Game game = gameMap.get(gameName);
				if (game == null) {
					game = new Game();
					game.setPrizes(new ArrayList<Prize>());
					game.setName(gameName);
					game.setGameNumber(StringUtils.trim(cells.get(0).html()));
					game.setGameCost(gameCost);
					// Keep track of the new game so we don't create another one
					gameMap.put(gameName, game);
					games.add(game);
				}
				
				game.getPrizes().add(getPrize(cells));
			}
			
			
		}
		return state;
	}
	
	private Prize getPrize(Elements cells) {
		Prize prize = new Prize();
				prize.setValue(convertNumber(cells.get(2).html()));
				prize.setRemainingPrizes(convertNumber(cells.get(3).select("span").get(0).html()));
		return prize;
	}
		
	@Override
	public String getURL() {
		return "http://www.delottery.com/games/igames/remaining.asp";
	}
}
