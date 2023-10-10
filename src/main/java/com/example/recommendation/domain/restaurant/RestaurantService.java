package com.example.recommendation.domain.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.recommend.model.Coordinate;
import com.example.recommendation.domain.restaurant.dto.RestaurantRequest;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;
import com.example.recommendation.domain.weather.AddressService;

@Service
public class RestaurantService {

	private final AddressService addressService;
	private final RestaurantSearchService restaurantSearchService;

	public RestaurantService(AddressService addressService, RestaurantSearchService restaurantSearchService) {
		this.addressService = addressService;
		this.restaurantSearchService = restaurantSearchService;
	}

	public List<RestaurantResponse> search(Coordinate coordinate, List<MenuResponse> menuResponses) {
		String address = addressService.get(coordinate);

		List<RestaurantRequest> restaurantRequests = RestaurantRequest.from(menuResponses, address);

		List<RestaurantResponse> restaurantResponses = new ArrayList<>();

		for (RestaurantRequest restaurantRequest : restaurantRequests) {
			restaurantResponses.add(restaurantSearchService.search(restaurantRequest));
		}

		return Collections.unmodifiableList(restaurantResponses);
	}
}
