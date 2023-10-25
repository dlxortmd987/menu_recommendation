package com.example.recommendation.presentation.dto;

import java.time.LocalDateTime;

import com.example.recommendation.domain.recommend.model.Coordinate;

public record RecommendRequest(
	Coordinate coordinate,
	LocalDateTime targetTime
) {
}
