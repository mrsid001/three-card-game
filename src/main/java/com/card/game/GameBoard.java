package com.card.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameBoard {

	Map<String, List<String>> finalList = new HashMap<>();
	List<Game> result = new ArrayList<Game>();

	private String setPlayerAndCard(Map<String, List<String>> playersCardDetails) {
		int totalPlayers = playersCardDetails.keySet().size();
     //Check for the minimum required players
		if (totalPlayers < 2) {
			String msg = "Not enough Players";
			System.out.println("This game requires at least 2 Players.");
			return msg;

		} else {
			finalList.putAll(playersCardDetails);
			System.out.println("Distributed Cards to all Players");
			System.out.println("---------------------------------");
			System.out.println(finalList);
			System.out.printf("%n%n");
		}
		return null;
	}

	private int cardValue(String cardVal) {
		int c = 0;
		for (Cards val : Cards.values()) {
			String card = val.getValue();
			if (card.equals(cardVal)) {
				c = val.getVal();
			}
		}
		return c;
	}

	public void cardIdentify() {
		int k = 0;
		int seq[] = new int[3];
		String payerCard;

		for (Map.Entry<String, List<String>> entry : finalList.entrySet()) {
			String player = entry.getKey();
			List<String> playerCards = entry.getValue();

			payerCard = finalList.get(player).toString();
			String first = playerCards.get(0);
			String second = playerCards.get(1);
			String third = playerCards.get(2);

			String cardType1 = first.split("-")[0];
			String cardType2 = second.split("-")[0];
			String cardType3 = third.split("-")[0];

			Integer suitValue1 = Integer.parseInt(Suits.valueOf(first.split("-")[1]).getValue());
			Integer suitValue2 = Integer.parseInt(Suits.valueOf(second.split("-")[1]).getValue());
			Integer suitValue3 = Integer.parseInt(Suits.valueOf(third.split("-")[1]).getValue());

			int totalSuitSum = suitValue1 + suitValue2 + suitValue3;

			Integer cardValue1 = cardValue(cardType1);
			Integer cardValue2 = cardValue(cardType2);
			Integer cardValue3 = cardValue(cardType3);

			seq[0] = cardValue1;
			seq[1] = cardValue2;
			seq[2] = cardValue3;

			Arrays.sort(seq);

			if (seq[2] == 14 && seq[0] == 2 && seq[1] == 3) {
				seq[0] = 1;
				seq[1] = 2;
				seq[2] = 3;
			}
			int total = totalSuitSum;
			int sum = 0;
			String gameRule = null;
			
			if (cardValue1.equals(cardValue2) && cardValue2.equals(cardValue3)) {
				total = total + 5000000;
				sum = priority(seq, total, sum);
				gameRule = "Three of a kind";
			
			} else if (seq[0] + 1 == seq[1] && seq[0] + 2 == seq[2]) { 
				total = total + 3000000;
				if (seq[0] == 1 && seq[1] == 2 && seq[2] == 3) {
					seq[0] = 2;
					seq[1] = 3;
					seq[2] = 14;
				}
				sum = priority(seq, total, sum);
				gameRule = "High Card";

			} else if (cardValue1.equals(cardValue2) || cardValue2.equals(cardValue3)
					|| cardValue3.equals(cardValue1)) {
				total = total + 500000;
				k = seq[1];
				for (int i = 1; i <= 14; i++) {
					if (k == i) {
						sum = total + seq[0] + seq[1] + seq[2] + (k * k * k);
						break;
					}
				}
				gameRule = "High Card";
			} else {
				sum = priority(seq, total, sum);
				gameRule = "High Card";
			}

			result.add(new Game(player, sum, payerCard, gameRule));
		}

	}

	public int priority(int seq[], int total, int sum) {
		int minSeq = 2;
		int maxSeq = 14;
		int[] seqValues = { 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 110000, 120000, 130000,
				140000 };
		total = total + seq[0] + seq[1] + seq[2];

		for (int i = minSeq; i <= maxSeq; i++) {
			if (seq[2] == i) {
				total += seqValues[i - minSeq];
				break;
			}
		}
		return total;
	}

	public String result() {

		result.sort(Comparator.comparing(g -> g.getPriority()));
		Collections.reverse(result);
		Game gameWinner = result.get(0);
		Set<Integer> checkDraw = new HashSet<Integer>();
		Map<Integer, List<String>> priorityAndPlayersMap = new HashMap<Integer, List<String>>();
		System.out.println("===========================================");
		System.out.println("GAME OVER");
		System.out.println("===========================================");

		for (Game game : result) {
			List<String> playersWithSamePriority = priorityAndPlayersMap.get(game.getPriority());
			if (playersWithSamePriority == null) {
				playersWithSamePriority = new ArrayList<String>();
			}
			playersWithSamePriority.add(game.getWinnerName());
			priorityAndPlayersMap.put(game.getPriority(), playersWithSamePriority);
			checkDraw.add(game.getPriority());
		}
		String msg = "Match has drawn";
		for (Map.Entry<Integer, List<String>> entry : priorityAndPlayersMap.entrySet()) {
			if (entry.getValue().size() > 1) {
				System.out.println("Match has drawn, since more than one player has same cards");
				return msg;
			}
		}
		List<Integer> sortedList = new ArrayList<Integer>(priorityAndPlayersMap.keySet());
		Collections.sort(sortedList);
		Integer winner = sortedList.get(sortedList.size() - 1);
		String gameWinnerName = priorityAndPlayersMap.get(winner).get(0);
		System.out.println(gameWinnerName + " has won by " + gameWinner.getGameRule() + " rule with Cards: "
				+ gameWinner.getCards());
		return gameWinnerName;
	}

	public String getPlayWinner(final Map<String, List<String>> playersCardDetails) {
		String response = null;
		String message = setPlayerAndCard(playersCardDetails);
		if (message == null) {
			cardIdentify();
			String gameResult = result();
			response = gameResult;
		} else {
			response = message;
		}
		return response;
	}

}
