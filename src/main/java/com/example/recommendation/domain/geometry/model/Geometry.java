package com.example.recommendation.domain.geometry.model;

import com.example.recommendation.domain.recommend.model.GeographicCoordinate;

public record Geometry(
	GeographicCoordinate geographicCoordinate,
	Address address,
	GpsCoordinate gpsCoordinate
) {
	public Geometry(GeographicCoordinate geographicCoordinate, Address address) {
		this(geographicCoordinate, address, GpsCoordinate.from(geographicCoordinate));
	}
}
