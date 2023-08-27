package com.example.recommendation.domain.menu.model.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.TimeSlot;
import com.example.recommendation.domain.weather.model.SkyStatus;
import com.example.recommendation.domain.weather.model.TemperatureStatus;
import com.example.recommendation.domain.weather.model.dto.WeatherResponse;

public record MenuRequest(
	TemperatureStatus temperature,
	SkyStatus skyStatus,
	TimeSlot timeSlot
) {
	public static List<MenuRequest> from(List<WeatherResponse> weatherResponses) {
		return weatherResponses.stream()
			.map(weatherResponse -> new MenuRequest(
				weatherResponse.weather().temperatureStatus(),
				weatherResponse.weather().skyStatus(),
				convert(weatherResponse.time())
			)).toList();
	}

	private static TimeSlot convert(String time) {

		return null;
	}
}
