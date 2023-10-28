package com.example.recommendation.infra.feign.naver.address.dto;

import java.util.List;

import com.example.recommendation.domain.geometry.model.Address;

public record NaverAddressClientResponse(
	NaverAddressClientResponseStatus status,
	List<NaverAddressClientResponseData> results
) {
	public Address toAddress() {
		return getRegion().getAddress();
	}

	private NaverAddressClientResponseRegion getRegion() {
		return results.get(0).region();
	}
}
