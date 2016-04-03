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
		System.out.println("row count: " + rows.size());
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
				
				// The game number cell
				Element cellOne = cells.get(0);
				String gameId = StringUtils.trim(cellOne.html());
				game.setGameNumber(gameId);
				
				// The value cell
				Element cellFive = cells.get(4);
				game.setGameCost(convertToNumber(cellFive.html()));
				
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
		
		// The prize value cell
		Element cellThree = cells.get(2);
		prize.setValue(convertToNumber(cellThree.html()));
		
		// The remaining prizes cell
		String cellFourString = cells.get(3).html();
		prize.setRemainingPrizes(beforeOf(cellFourString));
		
		prize.setTotalPrizes(afterOf(cellFourString));
		
		return prize;
	}
	
	private static int convertToNumber(String str) {
		String withoutDollar = StringUtils.remove(StringUtils.trim(str), "$");
		String withoutStar = StringUtils.remove(withoutDollar, "*");
		String withoutComma = StringUtils.remove(withoutStar, ",");
		String beforeDecimal = StringUtils.substringBefore(withoutComma,".");
		String beforeSpace = StringUtils.substringBefore(beforeDecimal, " ");
		String beforeSlash = StringUtils.substringBefore(beforeSpace, "/");
		int answer = 0;
		
		try {
			answer = Integer.parseInt(beforeSlash);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return answer;
	}
	
	private static int beforeOf(String str) {
		return convertToNumber(StringUtils.substringBefore(str, "of"));
	}
	
	private static int afterOf(String str) {
		return convertToNumber(StringUtils.substringAfter(str, "of"));
	}

}
