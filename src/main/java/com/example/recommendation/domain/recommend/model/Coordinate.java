package com.example.recommendation.domain.recommend.model;

public record Coordinate(
	Double latitude,
	Double longitude
) {

	public boolean isSame(Double latitude, Double longitude) {
		return isSameLongitude(longitude) && isSameLatitude(latitude);
	}

	private boolean isSameLatitude(Double latitude) {
		return this.latitude.compareTo(latitude) == 0;
	}

	private boolean isSameLongitude(Double longitude) {
		return this.longitude.compareTo(longitude) == 0;
	}
}
