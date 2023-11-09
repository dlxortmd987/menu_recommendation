package com.example.recommendation.domain.menu.model.dto;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.menu.model.entity.Menu;
import com.example.recommendation.domain.menu.model.entity.MenuRecommend;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;
import com.example.recommendation.domain.weather.model.Weather;

public record MenuResponse(
	MenuDetail menu,
	Weather weather,
	MealTime mealTime
) {

	public static MenuResponse from(FindAllMenusByConditionProjection projection) {
		return new MenuResponse(
			new MenuDetail(projection.getName(), projection.getReason()),
			new Weather(projection.getSky(), projection.getTemperature()),
			projection.getMealTime()
		);
	}

	public Menu toMenu() {
		return new Menu(menu.name());
	}

	public MenuRecommend toMenuRecommend(Menu savedMenu) {
		return new MenuRecommend(
			savedMenu,
			menu().reason(),
			weather.temperature(),
			weather.sky(),
			mealTime
		);
	}

	public String getMenuName() {
		return menu.name();
	}

	public Sky getSky() {
		return weather.sky();
	}

	public Temperature getTemperature() {
		return weather.temperature();
	}
}
