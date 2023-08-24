package com.example.recommendation.infra.feign.weather.dto;

import java.util.List;

import com.example.recommendation.domain.weather.model.dto.WeatherResponse;

public record WeatherCallLiveResponse(
	WeatherResponseDto response
) {

	public List<WeatherResponse> getWeatherList() {
		return response().body().getWeatherList();
	}

	public record WeatherResponseDto(
		WeatherResponseHeader header,
		WeatherResponseBody body
	) {
	}
}
