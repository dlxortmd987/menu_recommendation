package com.example.recommendation.domain.weather.model;

public enum TemperatureStatus {

	COLD,
	COOL,
	WARM,
	HOT;

	public static TemperatureStatus from(String value) {
		Double temperature = Double.parseDouble(value);
		if (temperature.compareTo(5.0) < 0) {
			return COLD;
		} else if (temperature.compareTo(12.0) < 0) {
			return COOL;
		} else if (temperature.compareTo(23.0) < 0) {
			return WARM;
		} else {
			return HOT;
		}
	}
}
