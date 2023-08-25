package com.example.recommendation.infra.feign.weather;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.recommendation.domain.weather.model.dto.WeatherResponse;
import com.example.recommendation.infra.feign.weather.dto.WeatherCallLiveResponse;
import com.example.recommendation.infra.feign.weather.utils.WeatherProperties;

@ExtendWith(MockitoExtension.class)
class WeatherOpenApiServiceTest {

	@Mock
	private WeatherClient weatherClient;

	@Spy
	private WeatherProperties weatherProperties;

	@InjectMocks
	private WeatherOpenApiService weatherOpenApiService;

	@BeforeEach
	void beforeAll() {
		weatherProperties.setKey("testKey");
		weatherProperties.setDataType("JSON");
	}

	@Test
	void get() {
		// given
		WeatherCallLiveResponse weatherCallLiveResponse = WeatherDtoProvider.getWeatherCallLiveResponse();
		List<WeatherResponse> expect = weatherCallLiveResponse.getWeatherList();

		BDDMockito.when(weatherClient.callLive(
			anyInt(),
			anyInt(),
			anyString(),
			anyString(),
			anyInt(),
			anyInt(),
			any(WeatherProperties.class)
		)).thenReturn(weatherCallLiveResponse);

		// when
		List<WeatherResponse> actual = weatherOpenApiService.get(126.970955, 37.573269, LocalDateTime.now());

		// then
		verify(weatherClient).callLive(
			anyInt(),
			anyInt(),
			anyString(),
			anyString(),
			anyInt(),
			anyInt(),
			any(WeatherProperties.class)
		);
		assertThat(actual).isEqualTo(expect);
	}
}