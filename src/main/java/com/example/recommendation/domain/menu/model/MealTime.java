package com.example.recommendation.domain.menu.model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public enum MealTime {

	AFTERNOON(LocalTime.of(12, 0)),
	EVENING(LocalTime.of(18, 0));

	private static final LocalTime AFTERNOON_MIN_THRESHOLD = LocalTime.of(11, 0);
	private static final LocalTime AFTERNOON_MAX_THRESHOLD = LocalTime.of(13, 0);

	private static final LocalTime EVENING_MIN_THRESHOLD = LocalTime.of(17, 0);
	private static final LocalTime EVENING_MAX_THRESHOLD = LocalTime.of(19, 0);

	private final LocalTime value;

	MealTime(LocalTime value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	public static boolean matches(LocalTime time) {
		return Arrays.stream(MealTime.values())
			.anyMatch(mealTime -> mealTime.value.equals(time));
	}

	public static MealTime find(LocalTime time) {
		if (isAfternoon(time)) {
			return AFTERNOON;
		} else if (isEvening(time)) {
			return EVENING;
		}

		throw new RuntimeException("해당 시간대는 식사 시간이 아닙니다.[" + time + "]");
	}

	private static boolean isEvening(LocalTime time) {
		return time.isAfter(EVENING_MIN_THRESHOLD) && time.isBefore(EVENING_MAX_THRESHOLD);
	}

	private static boolean isAfternoon(LocalTime time) {
		return time.isAfter(AFTERNOON_MIN_THRESHOLD) && time.isBefore(AFTERNOON_MAX_THRESHOLD);
	}

	public LocalTime getValue() {
		return value;
	}
}
