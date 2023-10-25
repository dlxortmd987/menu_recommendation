package com.example.recommendation.domain.menu.model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum MealTime {

	AFTERNOON(LocalTime.of(12, 0)),
	EVENING(LocalTime.of(6, 0));

	private final LocalTime value;

	MealTime(LocalTime value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	public static boolean matches(LocalTime time) {
		return Arrays.stream(MealTime.values())
			.anyMatch(mealTime -> mealTime.value.equals(time));
	}

	public static Optional<MealTime> find(LocalTime time) {
		return Arrays.stream(MealTime.values())
			.filter(mealTime -> mealTime.value.equals(time))
			.findFirst();
	}

	public LocalTime getValue() {
		return value;
	}
}
