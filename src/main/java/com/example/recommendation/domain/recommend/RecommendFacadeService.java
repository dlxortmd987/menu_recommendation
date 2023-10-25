package com.example.recommendation.domain.recommend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.MenuService;
import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.recommend.model.Coordinate;
import com.example.recommendation.domain.recommend.model.dto.RecommendResponse;
import com.example.recommendation.domain.restaurant.RestaurantService;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;
import com.example.recommendation.domain.weather.model.dto.WeatherResponseForTime;
import com.example.recommendation.domain.weather.service.WeatherService;

@Service
public class RecommendFacadeService {

	private final WeatherService weatherService;
	private final MenuService menuService;
	private final RestaurantService restaurantService;

	public RecommendFacadeService(WeatherService weatherService, MenuService menuService,
		RestaurantService restaurantService) {
		this.weatherService = weatherService;
		this.menuService = menuService;
		this.restaurantService = restaurantService;
	}

	public RecommendResponse recommend(Coordinate coordinate, LocalDateTime targetTime) {
		// 위치로 날씨 받기
		List<WeatherResponseForTime> weatherResponseForTimes = weatherService.search(coordinate, targetTime);

		// 날씨와 시간으로 메뉴 추천
		List<MenuResponse> menuResponses = recommendMenusAsync(
			weatherResponseForTimes);

		// 메뉴와 위치 기반으로 식당 추천
		List<RestaurantResponse> restaurantResponses = restaurantService.search(coordinate, menuResponses);

		return new RecommendResponse(menuResponses, restaurantResponses);
	}

	private List<MenuResponse> recommendMenusAsync(List<WeatherResponseForTime> weatherResponseForTimes) {
		List<CompletableFuture<MenuResponse>> futures = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			CompletableFuture<MenuResponse> menuResponseCompletableFuture = CompletableFuture.supplyAsync(
				() -> menuService.findMenuList(FindMenuRequest.from(weatherResponseForTimes)));

			futures.add(menuResponseCompletableFuture);
		}

		return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
			.thenApply(o -> futures.stream()
				.map(CompletableFuture::join)
				.toList())
			.join();
	}
}
