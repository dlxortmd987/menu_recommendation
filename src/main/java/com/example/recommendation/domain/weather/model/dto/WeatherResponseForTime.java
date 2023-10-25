package com.example.recommendation.domain.weather.model.dto;

import com.example.recommendation.domain.weather.model.SkyStatus;
import com.example.recommendation.domain.weather.model.TemperatureStatus;
import com.example.recommendation.domain.weather.model.Weather;

public record WeatherResponseForTime(
	Weather weather,
	String time
) {
	public WeatherResponseForTime(String code, String time) {
		this(new Weather(SkyStatus.from(code), TemperatureStatus.from(code)), time);
	}
}
