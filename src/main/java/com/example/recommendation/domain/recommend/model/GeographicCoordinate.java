package com.example.recommendation.domain.recommend.model;

public record GeographicCoordinate(
	Double latitude,
	Double longitude
) {

	private static final double EPSILON = 0.00001d;

	public boolean isSame(Double latitude, Double longitude) {
		return isSameLongitude(longitude) && isSameLatitude(latitude);
	}

	private boolean isSameLatitude(Double latitude) {
		return Math.abs(this.latitude - latitude) < EPSILON;
	}

	private boolean isSameLongitude(Double longitude) {
		return Math.abs(this.longitude - longitude) < EPSILON;
	}
}
