package com.example.recommendation.infra.feign.weather.dto;

import java.util.List;

import com.example.recommendation.domain.weather.model.dto.WeatherResponseForTime;

public record WeatherCallLiveResponse(
	WeatherResponseDto response
) {

	public List<WeatherResponseForTime> getWeatherList() {
		return response().body().getWeatherList();
	}

	public record WeatherResponseDto(
		WeatherResponseHeader header,
		WeatherResponseBody body
	) {
	}
}
