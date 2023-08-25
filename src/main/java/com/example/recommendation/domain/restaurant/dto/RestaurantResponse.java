package com.example.recommendation.domain.restaurant.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RestaurantResponse(
	List<RestaurantResponseDetail> details,
	LocalDateTime requestTime
) {
}
