package com.example.recommendation.domain.weather.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.recommendation.domain.recommend.model.Coordinate;
import com.example.recommendation.domain.weather.model.dto.WeatherResponseForTime;

public interface WeatherService {

	List<WeatherResponseForTime> search(Coordinate coordinate, LocalDateTime targetTime);
}
