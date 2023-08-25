package com.example.recommendation.domain.restaurant.dto;

public record RestaurantResponseDetail(
	String title,
	String link,
	String category,
	String description,
	String telephone,
	String address
) {
}
