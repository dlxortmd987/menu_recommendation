package com.example.recommendation.infra.feign.weather.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.menu.model.dto.MenuFindConditionRequest;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;

public record WeatherResponseBody(
	WeatherResponseItems items,
	int pageNo,
	int numOfRows,
	int totalCount
) {

	public MenuFindConditionRequest getMealTimeWeather() {
		return items.item()
			.stream()
			.filter(WeatherResponseItems.Item::isWeatherCategory)
			.filter(item -> isMealTime(item.fcstTime()))
			.findFirst()
			.map(item -> toCondition(item.fcstValue(), item.fcstTime()))
			.orElseThrow();
	}

	private MenuFindConditionRequest toCondition(String weatherValue, String time) {
		return new MenuFindConditionRequest(
			toSky(weatherValue),
			toTemperature(weatherValue),
			MealTime.find(convertFormat(time))
		);
	}

	private boolean isMealTime(String rawTime) {
		LocalTime time = convertFormat(rawTime);
		return MealTime.matches(time);
	}

	private LocalTime convertFormat(String rawTime) {
		return LocalTime.parse(rawTime, DateTimeFormatter.ofPattern("HHmm"));
	}

	private Sky toSky(String value) {
		return switch (value) {
			case "1", "2", "5", "6" -> Sky.RAINY;
			case "3", "7" -> Sky.SNOWY;
			default -> Sky.CLEAR;
		};
	}

	private Temperature toTemperature(String value) {
		Double temperature = Double.parseDouble(value);
		if (temperature.compareTo(5.0) < 0) {
			return Temperature.COLD;
		} else if (temperature.compareTo(12.0) < 0) {
			return Temperature.COOL;
		} else if (temperature.compareTo(23.0) < 0) {
			return Temperature.WARM;
		} else {
			return Temperature.HOT;
		}
	}
}
