package com.card.game;

public enum Suits {
	HEART("100"), SPADE("80"), DIAMOND("60"), CLUB("40");
	private final String value;

	Suits(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
