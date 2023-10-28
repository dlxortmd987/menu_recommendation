package com.example.recommendation.domain.weather.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.recommendation.domain.geometry.model.GpsCoordinate;
import com.example.recommendation.domain.weather.model.dto.WeatherResponseForTime;

public interface WeatherService {

	List<WeatherResponseForTime> search(GpsCoordinate gpsCoordinate, LocalDateTime targetTime);
}
