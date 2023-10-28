package com.example.recommendation.infra.feign.naver.address;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.geometry.AddressConvertService;
import com.example.recommendation.domain.geometry.model.Address;
import com.example.recommendation.domain.recommend.model.GeographicCoordinate;
import com.example.recommendation.infra.feign.naver.address.dto.NaverAddressClientResponse;

@Service
public class NaverAddressConvertService implements AddressConvertService {

	private static final String CLIENT_CALL_FORMAT = "%f,%f";
	private final NaverAddressClient naverAddressClient;

	public NaverAddressConvertService(NaverAddressClient naverAddressClient) {
		this.naverAddressClient = naverAddressClient;
	}

	private static String toClientResponse(GeographicCoordinate coordinate) {
		return CLIENT_CALL_FORMAT.formatted(coordinate.longitude(), coordinate.latitude());
	}

	public Address convert(GeographicCoordinate coordinate) {

		NaverAddressClientResponse clientResponse = naverAddressClient.call(toClientResponse(coordinate));

		return clientResponse.toAddress();
	}
}
