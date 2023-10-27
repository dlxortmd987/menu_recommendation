package com.example.recommendation.domain.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.geometry.model.Address;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.restaurant.dto.RestaurantRequest;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;

@Service
public class RestaurantService {

	private final RestaurantSearchService restaurantSearchService;

	public RestaurantService(RestaurantSearchService restaurantSearchService) {
		this.restaurantSearchService = restaurantSearchService;
	}

	public List<RestaurantResponse> search(Address address, List<MenuResponse> menuResponses) {
		List<RestaurantRequest> restaurantRequests = RestaurantRequest.from(menuResponses, address.value());

		List<RestaurantResponse> restaurantResponses = new ArrayList<>();

		for (RestaurantRequest restaurantRequest : restaurantRequests) {
			restaurantResponses.add(restaurantSearchService.search(restaurantRequest));
		}

		return Collections.unmodifiableList(restaurantResponses);
	}
}
