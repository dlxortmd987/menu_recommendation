package com.example.recommendation.infra.feign.weather.dto;

import java.util.List;

public record WeatherResponseItems(
	List<Item> item
) {
	public record Item(
		String category,
		String fcstTime,
		String fcstValue
	) {
		private static final String RAINFALL_CATEGORY = "PTY";
		private static final String TEMPERATURE_CATEGORY = "T1H";

		public boolean isSkyCategory() {
			return this.category().equals(RAINFALL_CATEGORY);
		}

		public boolean isTemperatureCategory() {
			return this.category().equals(TEMPERATURE_CATEGORY);
		}

		public boolean isWeatherCategory() {
			return isTemperatureCategory() || isSkyCategory();
		}
	}
}
