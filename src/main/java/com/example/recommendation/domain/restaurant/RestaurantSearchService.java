package com.example.recommendation.domain.restaurant;

import com.example.recommendation.domain.restaurant.dto.RestaurantRequest;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;

public interface RestaurantSearchService {

	RestaurantResponse search(RestaurantRequest request);
}
