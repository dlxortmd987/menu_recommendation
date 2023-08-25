package com.example.recommendation.domain.weather.menu.service.dto;

import java.util.List;

import com.example.recommendation.domain.weather.menu.model.TimeSlot;
import com.example.recommendation.domain.weather.model.Weather;

public record MenuResponse(
	List<MenuDetail> menus,
	Weather weather,
	TimeSlot timeSlot
) {
}
