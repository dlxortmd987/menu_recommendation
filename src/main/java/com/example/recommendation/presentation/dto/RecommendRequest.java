package com.example.recommendation.presentation.dto;

import java.time.LocalDateTime;

import com.example.recommendation.domain.recommend.model.GeographicCoordinate;

public record RecommendRequest(
	GeographicCoordinate coordinate,
	LocalDateTime targetTime
) {
}
