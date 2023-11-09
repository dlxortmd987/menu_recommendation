package com.example.recommendation.domain.weather.service;

import java.time.LocalDateTime;

import com.example.recommendation.domain.geometry.model.GpsCoordinate;
import com.example.recommendation.domain.menu.model.dto.MenuFindConditionRequest;

public interface WeatherService {

	MenuFindConditionRequest searchMealTimeWeather(GpsCoordinate gpsCoordinate, LocalDateTime targetTime);
}
