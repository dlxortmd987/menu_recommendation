package com.example.recommendation.infra.feign.weather.dto;

public record WeatherResponseHeader(
	String resultCode,
	String resultMsg
) {
}
