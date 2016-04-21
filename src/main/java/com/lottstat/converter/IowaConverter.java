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

public class IowaConverter extends Converter {
	protected IowaConverter() {
	}

	@Override
	public State convertState(String html) {
		State state = new State();
		state.setAbbrev(StateEnum.IOWA.getAbbreviation());
		state.setName(StateEnum.IOWA.toString());

		// This map is to keep track of which games have already been added
		Map<String, Game> gameMap = new HashMap<String, Game>();

		// This is the resulting list of games
		List<Game> games = new ArrayList<Game>();
		state.setGames(games);

		// Parse the html from the web page into a Jsoup Document
		Document doc = Jsoup.parse(html);

		// Get the rows from the prizesRemaining table
		Elements rows = doc.select("table tbody tr:not(:first-child)");
		Game game = null;
		for (Element row : rows) {
			Elements cells = row.select("td");
			if (cells.size() > 0) {
				String gameCost = getFont(cells.get(0));
				if (!StringUtils.equals(gameCost, "&nbsp;")) {
					String gameName = StringUtils.trim(getGameName(getFont(cells.get(1))));
					game = gameMap.get(gameName);
					if (game == null) {
						game = new Game();
						game.setPrizes(new ArrayList<Prize>());
						game.setName(gameName);
						game.setGameCost(convertNumber(gameCost));
						game.setGameNumber(StringUtils.trim(getGameNumber(getFont(cells.get(1)))));
						// game.setGameNumber(StringUtils.trim(cells.get(0).html()));

						// Keep track of the new game so we don't create another
						// one
						gameMap.put(gameName, game);
						games.add(game);
					}

				}
				game.getPrizes().add(getPrize(cells));
			}
		}

		return state;
	}

	private String getGameName(String html) {
		return StringUtils.substringBefore(html, "(");
	}

	private String getGameNumber(String html) {
		return StringUtils.substringBetween(html, "(#", ")");
	}

	private String getFont(Element cell) {
		return cell.select("font").get(0).html();
	}

	private Prize getPrize(Elements cells) {
		Prize prize = new Prize();
		prize.setValue(convertNumber(getFont(cells.get(2))));
		prize.setRemainingPrizes(convertNumber(getFont(cells.get(4))));
		prize.setTotalPrizes(prize.getRemainingPrizes() + convertNumber(getFont(cells.get(3))));

		return prize;
	}

	@Override
	public String getURL() {
		return "http://www.ialottery.com//Games/RemainingPrizes.asp";
	}
}
