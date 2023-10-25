package com.example.recommendation.infra.feign.weather;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.recommendation.infra.feign.weather.dto.WeatherCallLiveResponse;
import com.example.recommendation.infra.feign.weather.utils.WeatherProperties;

// @SpringBootTest
class WeatherClientTest {

	@Autowired
	private WeatherClient weatherClient;

	@Autowired
	private WeatherProperties weatherProperties;

	@DisplayName("실시간 날씨 정보를 open api를 통해 요청할 수 있다.")
	@Disabled
	@Test
	void callLive() {

		WeatherCallLiveResponse weatherCallLiveResponse = weatherClient.callLive(
			1000,
			1,
			"20230824",
			"0130",
			60,
			127,
			weatherProperties
		);

		System.out.println(weatherCallLiveResponse);
	}

	@Test
	void test() {
		LocalTime time = LocalTime.parse("1000", DateTimeFormatter.ofPattern("HHmm"));
		System.out.println("time = " + time);
	}
}