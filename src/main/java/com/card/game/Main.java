package com.card.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "P1";
		final String player2 = "P2";
		final String player3 = "P3";
		final String player4 = "P4";
		final String player5 = "P5";

		List<String> player1Cards = playerCards("SIX-CLUB", "SIX-DIAMOND", "SIX-CLUB");
		List<String> player2Cards = playerCards("TEN-SPADE", "THREE-SPADE", "JACK-SPADE");
		List<String> player3Cards = playerCards("THREE-CLUB", "SIX-HEART", "JACK-HEART");
		List<String> player4Cards = playerCards("NINE-CLUB", "TWO-HEART", "JACK-SPADE");
		List<String> player5Cards = playerCards("THREE-SPADE", "SIX-HEART", "JACK-HEART");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);
		playerCardDetails.put(player3, player3Cards);
		playerCardDetails.put(player4, player4Cards);
		playerCardDetails.put(player5, player5Cards);

		gameBoard.getPlayWinner(playerCardDetails);
	}

	private static List<String> playerCards(String card1, String card2, String card3) throws Exception {
		List<String> playerCards = new ArrayList<String>();
		playerCards.add(card1);
		playerCards.add(card2);
		playerCards.add(card3);
		return playerCards;
	}
}
