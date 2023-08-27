package com.example.recommendation.domain.menu.model;

import java.time.LocalTime;
import java.util.Objects;

public enum TimeSlot {

	AFTERNOON(LocalTime.of(12, 00)),
	EVENING(LocalTime.of(6, 0));

	private final LocalTime time;

	TimeSlot(LocalTime time) {
		Objects.requireNonNull(time);
		this.time = time;
	}

	public LocalTime getTime() {
		return time;
	}
}
