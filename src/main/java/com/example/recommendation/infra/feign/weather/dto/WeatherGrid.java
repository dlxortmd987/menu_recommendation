package com.example.recommendation.infra.feign.weather.dto;

public record WeatherGrid(
	Integer x,
	Integer y
) {
	public WeatherGrid(String x, String y) {
		this(Integer.parseInt(x), Integer.parseInt(y));
	}
}
