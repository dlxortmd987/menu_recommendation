package com.example.recommendation.domain.weather;

import com.example.recommendation.domain.recommend.model.Coordinate;

public interface AddressService {

	String get(Coordinate coordinate);
}
