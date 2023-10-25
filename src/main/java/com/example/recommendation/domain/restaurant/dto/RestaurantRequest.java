package com.example.recommendation.domain.restaurant.dto;

import java.util.Collection;
import java.util.List;

import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;

public record RestaurantRequest(
	String menu,
	String address
) {
	public static List<RestaurantRequest> from(List<MenuResponse> menuResponses, String address) {
		return menuResponses.stream()
			.map(MenuResponse::menus)
			.flatMap(Collection::stream)
			.map(MenuDetail::menu)
			.map(menu -> new RestaurantRequest(menu, address))
			.toList();
	}

	public static List<RestaurantRequest> from(MenuResponse menuResponse, String address) {
		return menuResponse.menus().stream()
			.map(MenuDetail::menu)
			.map(menu -> new RestaurantRequest(menu, address))
			.toList();
	}
}
