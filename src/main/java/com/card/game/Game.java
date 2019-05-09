package com.card.game;

public class Game {

	private String winnerName;
	private int priority;
	private String cards;
	private String gameRule;

	public Game(String winnerName, int priority, String cards, String gameRule) {
		super();
		this.winnerName = winnerName;
		this.priority = priority;
		this.cards = cards;
		this.gameRule = gameRule;

	}

	public String getWinnerName() {
		return winnerName;
	}

	public int getPriority() {
		return priority;
	}

	public String getCards() {
		return cards;
	}

	public String getGameRule() {
		return gameRule;
	}
}
