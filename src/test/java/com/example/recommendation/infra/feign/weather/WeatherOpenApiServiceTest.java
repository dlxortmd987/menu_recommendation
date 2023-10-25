package com.example.recommendation.infra.feign.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.recommendation.infra.feign.weather.utils.WeatherProperties;

@ExtendWith(MockitoExtension.class)
class WeatherOpenApiServiceTest {

	@Mock
	private WeatherClient weatherClient;

	@Spy
	private WeatherProperties weatherProperties;

	@InjectMocks
	private OpenApiWeatherService weatherOpenApiService;

	@BeforeEach
	void beforeAll() {
		weatherProperties.setKey("testKey");
		weatherProperties.setDataType("JSON");
	}

	// @Test
	// void get() {
	// 	// given
	// 	WeatherCallLiveResponse weatherCallLiveResponse = WeatherDtoProvider.getWeatherCallLiveResponse();
	// 	List<WeatherResponse> expect = weatherCallLiveResponse.getWeatherList();
	//
	// 	BDDMockito.when(weatherClient.callLive(
	// 		anyInt(),
	// 		anyInt(),
	// 		anyString(),
	// 		anyString(),
	// 		anyInt(),
	// 		anyInt(),
	// 		any(WeatherProperties.class)
	// 	)).thenReturn(weatherCallLiveResponse);
	//
	// 	// when
	// 	List<WeatherResponse> actual = weatherOpenApiService.search(126.970955, 37.573269, LocalDateTime.now());
	//
	// 	// then
	// 	verify(weatherClient).callLive(
	// 		anyInt(),
	// 		anyInt(),
	// 		anyString(),
	// 		anyString(),
	// 		anyInt(),
	// 		anyInt(),
	// 		any(WeatherProperties.class)
	// 	);
	// 	assertThat(actual).isEqualTo(expect);
	// }
}