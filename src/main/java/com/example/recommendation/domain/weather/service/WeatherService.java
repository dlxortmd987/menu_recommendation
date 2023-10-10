package com.example.recommendation.domain.weather.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.recommendation.domain.recommend.model.Coordinate;
import com.example.recommendation.domain.weather.model.dto.WeatherResponse;

public interface WeatherService {

	List<WeatherResponse> search(Coordinate coordinate, LocalDateTime targetTime);
}
