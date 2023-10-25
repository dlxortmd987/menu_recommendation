package com.example.recommendation.domain.recommend.model.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;

public record RecommendResponse(
	List<MenuResponse> menuResponses,
	List<RestaurantResponse> restaurantResponses
) {

}
