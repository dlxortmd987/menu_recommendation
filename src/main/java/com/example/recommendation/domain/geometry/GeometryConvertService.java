package com.example.recommendation.domain.geometry;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.geometry.model.Address;
import com.example.recommendation.domain.geometry.model.Geometry;
import com.example.recommendation.domain.recommend.model.GeographicCoordinate;

@Service
public class GeometryConvertService {

	private final AddressConvertService addressConvertService;

	public GeometryConvertService(AddressConvertService addressConvertService) {
		this.addressConvertService = addressConvertService;
	}

	public Geometry convert(GeographicCoordinate geographicCoordinate) {
		Address address = addressConvertService.convert(geographicCoordinate);
		return new Geometry(geographicCoordinate, address);
	}
}
