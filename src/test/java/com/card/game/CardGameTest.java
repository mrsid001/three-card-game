package com.card.game;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class CardGameTest {

	@Test
	public void onePlayersTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";

		List<String> player1Cards = playerCards("TEN-CLUB", "JACK-HEART", "JACK-DIAMOND");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, "Not enough Players");
	}

	@Test
	public void twoPlayersHighCardWinningTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";

		List<String> player1Cards = playerCards("TEN-CLUB", "JACK-HEART", "JACK-DIAMOND");
		List<String> player2Cards = playerCards("NINE-CLUB", "THREE-HEART", "EIGHT-DIAMOND");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, player1);
	}

	@Test
	public void threePlayersHighCardWinningTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";
		final String player3 = "Phill";

		List<String> player1Cards = playerCards("TEN-CLUB", "THREE-HEART", "JACK-DIAMOND");
		List<String> player2Cards = playerCards("NINE-CLUB", "THREE-HEART", "JACK-SPADE");
		List<String> player3Cards = playerCards("THREE-CLUB", "SIX-HEART", "JACK-HEART");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);
		playerCardDetails.put(player3, player3Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, player3);
	}

	@Test
	public void twoPlayersThreeOfKindWinningTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";

		List<String> player1Cards = playerCards("JACK-CLUB", "JACK-HEART", "JACK-DIAMOND");
		List<String> player2Cards = playerCards("ACE-CLUB", "KING-HEART", "THREE-DIAMOND");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, player1);
	}

	@Test
	public void twoPlayersThreeOfKindHighRankWinningTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";

		List<String> player1Cards = playerCards("JACK-CLUB", "JACK-HEART", "JACK-DIAMOND");
		List<String> player2Cards = playerCards("ACE-CLUB", "ACE-SPADE", "ACE-DIAMOND");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, player2);
	}

	@Test
	public void twoPlayersDrawTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";

		List<String> player1Cards = playerCards("TEN-CLUB", "THREE-HEART", "JACK-DIAMOND");
		List<String> player2Cards = playerCards("TEN-CLUB", "THREE-HEART", "JACK-DIAMOND");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, "Match has drawn");
	}

	@Test
	public void threePlayersDrawTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";
		final String player3 = "Phill";

		List<String> player1Cards = playerCards("TEN-CLUB", "THREE-HEART", "JACK-DIAMOND");
		List<String> player2Cards = playerCards("TEN-CLUB", "THREE-HEART", "JACK-DIAMOND");
		List<String> player3Cards = playerCards("THREE-CLUB", "SIX-HEART", "JACK-HEART");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);
		playerCardDetails.put(player3, player3Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, "Match has drawn");
	}

	@Test
	public void threePlayersTrueSequenceWinningTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";
		final String player3 = "Phill";

		List<String> player1Cards = playerCards("TWO-CLUB", "THREE-CLUB", "ACE-CLUB");
		List<String> player2Cards = playerCards("TEN-CLUB", "THREE-HEART", "JACK-DIAMOND");
		List<String> player3Cards = playerCards("THREE-CLUB", "SIX-HEART", "JACK-HEART");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);
		playerCardDetails.put(player3, player3Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, player1);
	}

	@Test
	public void threePlayersSequenceWinningTest() {

		GameBoard gameBoard = new GameBoard();

		final String player1 = "John";
		final String player2 = "Bob";
		final String player3 = "Phill";

		List<String> player1Cards = playerCards("SIX-HEART", "SIX-CLUB", "SIX-SPADE");
		List<String> player2Cards = playerCards("TEN-CLUB", "TWO-CLUB", "JACK-CLUB");
		List<String> player3Cards = playerCards("THREE-CLUB", "SIX-HEART", "JACK-HEART");

		Map<String, List<String>> playerCardDetails = new HashMap<String, List<String>>();
		playerCardDetails.put(player1, player1Cards);
		playerCardDetails.put(player2, player2Cards);
		playerCardDetails.put(player3, player3Cards);

		String winner = gameBoard.getPlayWinner(playerCardDetails);

		assertEquals(winner, player1);
	}

	private List<String> playerCards(String card1, String card2, String card3) {
		List<String> playerCards = new ArrayList<String>();
		playerCards.add(card1);
		playerCards.add(card2);
		playerCards.add(card3);
		return playerCards;
	}

}
