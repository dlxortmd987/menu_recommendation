package com.example.recommendation.infra.feign.weather.dto;

public record WeatherCallLiveResponse(
	WeatherResponseDto response
) {

	public record WeatherResponseDto(
		WeatherResponseHeader header,
		WeatherResponseBody body
	) {
	}
}
