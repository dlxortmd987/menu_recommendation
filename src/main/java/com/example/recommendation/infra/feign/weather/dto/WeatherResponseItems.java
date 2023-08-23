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
	}
}
