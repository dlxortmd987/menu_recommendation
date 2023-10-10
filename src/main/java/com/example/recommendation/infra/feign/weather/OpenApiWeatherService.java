package com.example.recommendation.infra.feign.weather;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.recommend.model.Coordinate;
import com.example.recommendation.domain.weather.model.dto.WeatherResponse;
import com.example.recommendation.domain.weather.service.WeatherService;
import com.example.recommendation.infra.feign.weather.dto.WeatherCallLiveResponse;
import com.example.recommendation.infra.feign.weather.dto.WeatherGrid;
import com.example.recommendation.infra.feign.weather.utils.WeatherGridCsvConvertor;
import com.example.recommendation.infra.feign.weather.utils.WeatherProperties;

@Service
public class OpenApiWeatherService implements WeatherService {

	private static final int DEFAULT_NUM_OF_ROWS = 30;
	private static final int DEFAULT_PAGE_NO = 1;

	private static final DateTimeFormatter BASE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final String WEATHER_TIME_FORMAT = "hhmm";

	private static final int BASE_TIME_CRITERIA = 45;
	private static final int BASE_TIME_FIX_NUMBER = 30;

	private final WeatherClient weatherClient;
	private final WeatherProperties weatherProperties;

	public OpenApiWeatherService(WeatherClient weatherClient, WeatherProperties weatherProperties) {
		this.weatherClient = weatherClient;
		this.weatherProperties = weatherProperties;
	}

	@Override
	public List<WeatherResponse> search(Coordinate coordinate, LocalDateTime targetTime) {
		WeatherGrid weatherGrid = WeatherGridCsvConvertor.convert(coordinate);

		WeatherCallLiveResponse response = weatherClient.callLive(
			DEFAULT_NUM_OF_ROWS,
			DEFAULT_PAGE_NO,
			targetTime.format(BASE_DATE_FORMAT),
			fixBaseTime(targetTime.toLocalTime()),
			weatherGrid.x(),
			weatherGrid.y(),
			weatherProperties
		);

		return response.getWeatherList();
	}

	private String fixBaseTime(LocalTime time) {
		if (time.getMinute() <= BASE_TIME_CRITERIA) {
			time = time.withHour(time.getHour() - 1);
		}
		return time.withMinute(BASE_TIME_FIX_NUMBER)
			.format(DateTimeFormatter.ofPattern(WEATHER_TIME_FORMAT));
	}
}
