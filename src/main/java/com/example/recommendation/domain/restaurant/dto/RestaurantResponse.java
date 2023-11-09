package com.example.recommendation.domain.restaurant.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.recommendation.domain.menu.model.dto.MenuDetail;

public record RestaurantResponse(
	List<RestaurantResponseDetail> details,
	MenuDetail menuDetail,
	LocalDateTime requestTime
) {
}
