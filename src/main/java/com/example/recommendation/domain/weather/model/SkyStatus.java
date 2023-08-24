package com.example.recommendation.domain.weather.model;

public enum SkyStatus {

	CLEAR,
	RAINY,
	SNOWY;

	public static SkyStatus from(String code) {
		return switch (code) {
			case "0" -> CLEAR;
			case "1", "2", "5", "6" -> RAINY;
			case "3", "7" -> SNOWY;
			default -> CLEAR;
		};
	}
}
