package com.example.recommendation.domain.weather.menu.model.dto;

import com.example.recommendation.domain.weather.menu.model.TimeSlot;
import com.example.recommendation.domain.weather.model.SkyStatus;
import com.example.recommendation.domain.weather.model.TemperatureStatus;

public record MenuRequest(
	TemperatureStatus temperature,
	SkyStatus skyStatus,
	TimeSlot timeSlot
) {
}
