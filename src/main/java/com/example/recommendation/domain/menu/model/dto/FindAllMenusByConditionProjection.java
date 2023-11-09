package com.example.recommendation.domain.menu.model.dto;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;

public interface FindAllMenusByConditionProjection {

	String getName();

	String getReason();

	Sky getSky();

	Temperature getTemperature();

	MealTime getMealTime();
}
