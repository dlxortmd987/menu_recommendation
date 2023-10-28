package com.example.recommendation.infra.feign.naver.address.dto;

import com.example.recommendation.domain.geometry.model.Address;

public record NaverAddressClientResponseRegion(
	Area area1,
	Area area2,
	Area area3,
	Area area4
) {

	private static final String ADDRESS_FORMAT = "%s %s %s %s";

	public Address getAddress() {
		String value = ADDRESS_FORMAT.formatted(area1.name(), area2.name(), area3.name(), area4.name())
			.strip();

		return new Address(value);
	}

	public record Area(
		String name
	) {

	}
}
