package com.example.recommendation.domain.weather.model;

public record Weather(
	SkyStatus skyStatus,
	TemperatureStatus temperatureStatus
) {

}
