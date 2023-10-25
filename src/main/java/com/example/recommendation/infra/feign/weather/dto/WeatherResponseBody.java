package com.example.recommendation.infra.feign.weather.dto;

import java.util.List;

import com.example.recommendation.domain.weather.model.dto.WeatherResponseForTime;

public record WeatherResponseBody(
	WeatherResponseItems items,
	int pageNo,
	int numOfRows,
	int totalCount
) {

	public List<WeatherResponseForTime> getWeatherList() {
		return items.item()
			.stream()
			.filter(WeatherResponseItems.Item::isWeatherCategory)
			.map(item -> new WeatherResponseForTime(item.fcstValue(), item.fcstTime()))
			.toList();
	}
}
