package com.example.recommendation.domain.recommend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.MenuService;
import com.example.recommendation.domain.weather.model.dto.WeatherResponse;
import com.example.recommendation.domain.weather.service.WeatherFindService;

@Service
public class RecommendService {

	private final WeatherFindService weatherFindService;
	private final MenuService menuService;

	public RecommendService(WeatherFindService weatherFindService, MenuService menuService) {
		this.weatherFindService = weatherFindService;
		this.menuService = menuService;
	}

	public void recommend(Double latitude, Double longitude, LocalDateTime targetTime) {
		// 위치로 날씨 받기
		List<WeatherResponse> weatherResponses = weatherFindService.get(latitude, longitude, targetTime);

		// 날씨와 시간으로 메뉴 추천
		// menuService.findMenuList(MenuRequest.from(weatherResponses));

		// 메뉴와 위치 기반으로 식당 추천

	}
}
