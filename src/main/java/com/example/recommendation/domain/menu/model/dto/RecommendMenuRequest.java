package com.example.recommendation.domain.menu.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;
import com.example.recommendation.domain.weather.model.Weather;

public record RecommendMenuRequest(
	Weather weather,
	MealTime mealTime
) {
	public static RecommendMenuRequest from(Weather weather, LocalDateTime time) {
		return new RecommendMenuRequest(weather, MealTime.find(time.toLocalTime()));
	}

	public static List<RecommendMenuRequest> getAllCombination() {
		List<RecommendMenuRequest> requests = new ArrayList<>();

		for (Temperature temperature : Temperature.values()) {
			for (Sky sky : Sky.values()) {
				for (MealTime mealTime : MealTime.values()) {
					requests.add(new RecommendMenuRequest(new Weather(sky, temperature), mealTime));
				}
			}
		}

		return Collections.unmodifiableList(requests);
	}
}
