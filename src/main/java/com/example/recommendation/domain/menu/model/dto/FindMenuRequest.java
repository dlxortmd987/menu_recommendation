package com.example.recommendation.domain.menu.model.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.weather.model.SkyStatus;
import com.example.recommendation.domain.weather.model.TemperatureStatus;
import com.example.recommendation.domain.weather.model.dto.WeatherResponseForTime;

public record FindMenuRequest(
	TemperatureStatus temperature,
	SkyStatus skyStatus,
	MealTime mealTime
) {
	public static FindMenuRequest from(List<WeatherResponseForTime> weatherResponseForTimes) {
		return weatherResponseForTimes.stream()
			.filter(FindMenuRequest::isTimeToEat)
			.map(weatherResponse -> new FindMenuRequest(
				weatherResponse.weather().temperatureStatus(),
				weatherResponse.weather().skyStatus(),
				convert(weatherResponse.time())))
			.findFirst()
			.orElseThrow();
	}

	private static boolean isTimeToEat(WeatherResponseForTime weatherResponseForTime) {
		LocalTime time = LocalTime.parse(weatherResponseForTime.time(), DateTimeFormatter.ofPattern("HHmm"));
		return MealTime.matches(time);
	}

	private static MealTime convert(String time) {
		return MealTime.find(LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm")))
			.orElseThrow();
	}
}
