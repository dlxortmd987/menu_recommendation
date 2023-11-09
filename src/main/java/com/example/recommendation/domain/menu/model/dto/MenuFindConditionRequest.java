package com.example.recommendation.domain.menu.model.dto;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;

public record MenuFindConditionRequest(
	Sky sky,
	Temperature temperature,
	MealTime mealTime
) {
}
