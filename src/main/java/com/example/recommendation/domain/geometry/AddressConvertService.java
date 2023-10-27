package com.example.recommendation.domain.geometry;

import com.example.recommendation.domain.geometry.model.Address;
import com.example.recommendation.domain.recommend.model.GeographicCoordinate;

public interface AddressConvertService {

	Address convert(GeographicCoordinate coordinate);
}
