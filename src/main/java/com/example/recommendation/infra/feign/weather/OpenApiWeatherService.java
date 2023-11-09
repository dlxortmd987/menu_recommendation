package com.example.recommendation.infra.feign.weather;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.geometry.model.GpsCoordinate;
import com.example.recommendation.domain.menu.model.dto.MenuFindConditionRequest;
import com.example.recommendation.domain.weather.service.WeatherService;
import com.example.recommendation.infra.feign.weather.dto.WeatherCallLiveResponse;
import com.example.recommendation.infra.feign.weather.utils.WeatherProperties;

@Service
public class OpenApiWeatherService implements WeatherService {

	private static final int DEFAULT_NUM_OF_ROWS = 30;
	private static final int DEFAULT_PAGE_NO = 1;

	private static final DateTimeFormatter BASE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final String WEATHER_TIME_FORMAT = "HHmm";

	private static final int BASE_TIME_CRITERIA = 45;
	private static final int BASE_TIME_FIX_NUMBER = 30;

	private final WeatherClient weatherClient;
	private final WeatherProperties weatherProperties;

	public OpenApiWeatherService(WeatherClient weatherClient, WeatherProperties weatherProperties) {
		this.weatherClient = weatherClient;
		this.weatherProperties = weatherProperties;
	}

	@Override
	public MenuFindConditionRequest searchMealTimeWeather(GpsCoordinate gpsCoordinate, LocalDateTime targetTime) {
		WeatherCallLiveResponse response = weatherClient.callLive(
			DEFAULT_NUM_OF_ROWS,
			DEFAULT_PAGE_NO,
			targetTime.format(BASE_DATE_FORMAT),
			fixBaseTime(targetTime.toLocalTime()),
			gpsCoordinate.getIntegerX(),
			gpsCoordinate.getIntegerY(),
			weatherProperties
		);

		return response.getMealTimeWeather();
	}

	private String fixBaseTime(LocalTime time) {
		if (time.getMinute() <= BASE_TIME_CRITERIA) {
			time = time.withHour(time.getHour() - 1);
		}
		return time.withMinute(BASE_TIME_FIX_NUMBER)
			.format(DateTimeFormatter.ofPattern(WEATHER_TIME_FORMAT));
	}
}
