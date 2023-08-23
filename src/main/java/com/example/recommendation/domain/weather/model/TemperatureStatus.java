package com.example.recommendation.domain.weather.model;

import java.util.Objects;

public enum TemperatureStatus {

	COLD("추운"),
	COOL("시원한"),
	WARM("따뜻한"),
	HOT("더운");

	String korean;

	TemperatureStatus(String korean) {
		this.korean = Objects.requireNonNull(korean);
	}

	public String getKorean() {
		return korean;
	}
}
