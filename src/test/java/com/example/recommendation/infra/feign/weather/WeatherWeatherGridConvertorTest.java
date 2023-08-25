package com.example.recommendation.infra.feign.weather;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.recommendation.infra.feign.weather.dto.WeatherGrid;
import com.example.recommendation.infra.feign.weather.utils.WeatherGridConvertor;

class WeatherWeatherGridConvertorTest {

	@Test
	void get() {
		WeatherGrid expect = new WeatherGrid(60, 127);

		WeatherGrid actual = WeatherGridConvertor.get(126.970955, 37.573269);

		assertThat(actual)
			.isEqualTo(expect);
	}
}