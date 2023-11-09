package com.example.recommendation.domain.restaurant.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;

public record RestaurantRequest(
	MenuDetail menuDetail,
	String address
) {
	public static List<RestaurantRequest> from(List<MenuResponse> menuResponses, String address) {
		return menuResponses.stream()
			.map(MenuResponse::menu)
			.map(menuDetail -> new RestaurantRequest(menuDetail, address))
			.toList();
	}
}
