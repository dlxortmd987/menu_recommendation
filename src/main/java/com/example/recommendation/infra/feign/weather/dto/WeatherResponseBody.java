package com.example.recommendation.infra.feign.weather.dto;

public record WeatherResponseBody(
	WeatherResponseItems items,
	int pageNo,
	int numOfRows,
	int totalCount
) {
}
