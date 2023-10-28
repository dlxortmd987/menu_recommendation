package com.example.recommendation.domain.recommend;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.geometry.GeometryConvertService;
import com.example.recommendation.domain.geometry.model.Geometry;
import com.example.recommendation.domain.menu.MenuRecommendService;
import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.recommend.model.GeographicCoordinate;
import com.example.recommendation.domain.recommend.model.dto.RecommendResponse;
import com.example.recommendation.domain.restaurant.RestaurantService;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;
import com.example.recommendation.domain.weather.model.dto.WeatherResponseForTime;
import com.example.recommendation.domain.weather.service.WeatherService;

@Service
public class RecommendFacadeService {

	private static final int MENU_RECOMMEND_THREAD = 3;
	private final GeometryConvertService geometryConvertService;
	private final WeatherService weatherService;
	private final MenuRecommendService menuRecommendService;
	private final RestaurantService restaurantService;

	public RecommendFacadeService(GeometryConvertService geometryConvertService, WeatherService weatherService,
		MenuRecommendService menuRecommendService, RestaurantService restaurantService) {
		this.geometryConvertService = geometryConvertService;
		this.weatherService = weatherService;
		this.menuRecommendService = menuRecommendService;
		this.restaurantService = restaurantService;
	}

	public RecommendResponse recommend(GeographicCoordinate geographicCoordinate, LocalDateTime targetTime) {
		// 좌표로 주소 및 x, y좌표 변환
		Geometry geometry = geometryConvertService.convert(geographicCoordinate);

		// 위치로 날씨 받기
		List<WeatherResponseForTime> weatherResponseForTimes = weatherService.search(
			geometry.gpsCoordinate(),
			targetTime
		);

		// 날씨와 시간으로 메뉴 추천
		List<MenuResponse> menuResponses = menuRecommendService.recommend(
			FindMenuRequest.from(weatherResponseForTimes));

		// 메뉴와 위치 기반으로 식당 추천
		List<RestaurantResponse> restaurantResponses = restaurantService.search(
			geometry.address(),
			menuResponses
		);

		return new RecommendResponse(menuResponses, restaurantResponses);
	}
}
