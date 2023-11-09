package com.example.recommendation.infra.feign.weather.dto;

import com.example.recommendation.domain.menu.model.dto.MenuFindConditionRequest;

public record WeatherCallLiveResponse(
	WeatherResponseDto response
) {

	public MenuFindConditionRequest getMealTimeWeather() {
		return response().body().getMealTimeWeather();
	}

	public record WeatherResponseDto(
		WeatherResponseHeader header,
		WeatherResponseBody body
	) {
	}
}
