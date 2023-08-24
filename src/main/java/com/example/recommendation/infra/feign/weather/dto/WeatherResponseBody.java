package com.example.recommendation.infra.feign.weather.dto;

import java.util.List;

import com.example.recommendation.domain.weather.model.dto.WeatherResponse;

public record WeatherResponseBody(
	WeatherResponseItems items,
	int pageNo,
	int numOfRows,
	int totalCount
) {

	public List<WeatherResponse> getWeatherList() {
		return items.item()
			.stream()
			.filter(WeatherResponseItems.Item::isWeatherCategory)
			.map(item -> new WeatherResponse(item.fcstValue(), item.fcstTime()))
			.toList();
	}
}
