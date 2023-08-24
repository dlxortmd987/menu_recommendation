package com.example.recommendation.domain.weather.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.recommendation.domain.weather.model.dto.WeatherResponse;

public interface WeatherService {

	List<WeatherResponse> get(Double latitude, Double longitude, LocalDateTime targetTime);
}
