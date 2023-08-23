package com.example.recommendation.infra.feign.weather.dto;

import java.util.List;

public record WeatherResponseItems(
	List<Item> item
) {
	public record Item(
		int numOfRows,
		int pageNo,
		int totalCount,
		String category,
		double obsrValue
	) {
	}
}
