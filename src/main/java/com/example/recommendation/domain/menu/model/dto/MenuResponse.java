package com.example.recommendation.domain.menu.model.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.TimeSlot;
import com.example.recommendation.domain.weather.model.Weather;

public record MenuResponse(
	List<MenuDetail> menus,
	Weather weather,
	TimeSlot timeSlot
) {
}
