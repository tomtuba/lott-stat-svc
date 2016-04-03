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

public class FloridaConverter {
	public State convertState(String html) {
		State state = new State();
		state.setAbbrev("FL");
		state.setName("Florida");

		Map<String, Game> gameMap = new HashMap<String, Game>();
		List<Game> games = new ArrayList<Game>();
		state.setGames(games);
		Document doc = Jsoup.parse(html);
		Elements rows = doc.select(".prizesRemaining tbody tr");
		System.out.println("row count: " + rows.size());
		for (Element row : rows) {
			Elements cells = row.select("td");
			
			Element cellTwo = cells.get(1).select("a").get(0);
			String gameName = StringUtils.trim(cellTwo.html());

			Game game = gameMap.get(gameName);
			if (game == null) {
				game = new Game();
				game.setPrizes(new ArrayList<Prize>());
				game.setName(gameName);
				
				Element cellOne = cells.get(0);
				String gameId = StringUtils.trim(cellOne.html());
				game.setGameNumber(gameId);
				
				Element cellFive = cells.get(4);
				game.setGameCost(convertToNumber(cellFive.html()));
				
				gameMap.put(gameName, game);
				games.add(game);
			}
			
			Prize prize = new Prize();
			
			Element cellThree = cells.get(2);
			prize.setValue(convertToNumber(cellThree.html()));
			
			String cellFourString = cells.get(3).html();
			prize.setRemainingPrizes(beforeOf(cellFourString));
			
			prize.setTotalPrizes(afterOf(cellFourString));
			
			game.getPrizes().add(prize);
		}

		return state;
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
