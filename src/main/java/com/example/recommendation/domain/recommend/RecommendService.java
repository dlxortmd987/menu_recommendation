package com.example.recommendation.domain.recommend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.MenuService;
import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.recommend.model.Coordinate;
import com.example.recommendation.domain.restaurant.RestaurantService;
import com.example.recommendation.domain.weather.model.dto.WeatherResponse;
import com.example.recommendation.domain.weather.service.WeatherService;

@Service
public class RecommendService {

	private final WeatherService weatherService;
	private final MenuService menuService;
	private final RestaurantService restaurantService;

	public RecommendService(WeatherService weatherService, MenuService menuService,
		RestaurantService restaurantService) {
		this.weatherService = weatherService;
		this.menuService = menuService;
		this.restaurantService = restaurantService;
	}

	public void recommend(Coordinate coordinate, LocalDateTime targetTime) {
		// 위치로 날씨 받기
		List<WeatherResponse> weatherResponses = weatherService.search(coordinate, targetTime);

		// 날씨와 시간으로 메뉴 추천
		List<MenuResponse> menuResponses = menuService.findMenuList(FindMenuRequest.from(weatherResponses));

		// 메뉴와 위치 기반으로 식당 추천
		restaurantService.search(coordinate, menuResponses);
	}
}
