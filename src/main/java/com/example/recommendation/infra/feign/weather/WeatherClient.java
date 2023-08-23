package com.example.recommendation.infra.feign.weather;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recommendation.infra.feign.weather.dto.WeatherCallLiveResponse;

@FeignClient(name = "Weather", url = "${weather.url}")
public interface WeatherClient {

	@GetMapping("/getUltraSrtFcst")
	WeatherCallLiveResponse callLive(
		@RequestParam(value = "serviceKey") String serviceKey,
		@RequestParam(value = "numOfRows") int numOfRows,
		@RequestParam(value = "pageNo") int pageNo,
		@RequestParam(value = "dataType") String dataType,
		@RequestParam(value = "base_date") String baseDate,
		@RequestParam(value = "base_time") String baseTime,
		@RequestParam(value = "nx") int nx,
		@RequestParam(value = "ny") int ny
	);

	default WeatherCallLiveResponse callLive(
		int numOfRows,
		int pageNo,
		String base_date,
		String base_time,
		int nx,
		int ny,
		WeatherProperties weatherProperties
	) {
		return this.callLive(
			weatherProperties.getKey(),
			numOfRows,
			pageNo,
			weatherProperties.getDataType(),
			base_date,
			base_time,
			nx,
			ny
		);
	}
}
