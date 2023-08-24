package com.example.recommendation.infra.feign.weather;

import java.util.List;

import com.example.recommendation.infra.feign.weather.dto.WeatherCallLiveResponse;
import com.example.recommendation.infra.feign.weather.dto.WeatherResponseBody;
import com.example.recommendation.infra.feign.weather.dto.WeatherResponseHeader;
import com.example.recommendation.infra.feign.weather.dto.WeatherResponseItems;

public class WeatherDtoProvider {

	public static WeatherCallLiveResponse getWeatherCallLiveResponse() {
		WeatherResponseHeader header = new WeatherResponseHeader("00", "NORMAL_SERVICE");
		WeatherResponseItems.Item t1 = new WeatherResponseItems.Item("T1H", "1200", "11");
		WeatherResponseItems.Item t2 = new WeatherResponseItems.Item("T1H", "1300", "11");
		WeatherResponseItems.Item t3 = new WeatherResponseItems.Item("T1H", "1400", "11");
		WeatherResponseItems.Item t4 = new WeatherResponseItems.Item("T1H", "1500", "11");
		WeatherResponseItems.Item t5 = new WeatherResponseItems.Item("T1H", "1600", "11");
		WeatherResponseItems.Item t6 = new WeatherResponseItems.Item("T1H", "1700", "11");
		WeatherResponseItems.Item p1 = new WeatherResponseItems.Item("PTY", "1200", "11");
		WeatherResponseItems.Item p2 = new WeatherResponseItems.Item("PTY", "1300", "11");
		WeatherResponseItems.Item p3 = new WeatherResponseItems.Item("PTY", "1400", "11");
		WeatherResponseItems.Item p4 = new WeatherResponseItems.Item("PTY", "1500", "11");
		WeatherResponseItems.Item p5 = new WeatherResponseItems.Item("PTY", "1600", "11");
		WeatherResponseItems.Item p6 = new WeatherResponseItems.Item("PTY", "1700", "11");

		List<WeatherResponseItems.Item> items = List.of(t1, t2, t3, t4, t5, t6, p1, p2, p3, p4, p5, p6);
		WeatherResponseBody body = new WeatherResponseBody(new WeatherResponseItems(items), 1, 1, 1);
		return new WeatherCallLiveResponse(new WeatherCallLiveResponse.WeatherResponseDto(header, body));
	}
}
