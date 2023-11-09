package com.example.recommendation.domain.menu.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.menu.model.dto.FindAllMenusByConditionProjection;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MenuRecommendRepositoryTest {

	@Autowired
	private MenuRecommendRepository menuRecommendRepository;

	@Test
	void findAllByCondition() {

		List<FindAllMenusByConditionProjection> allByCondition = menuRecommendRepository.findAllByCondition(
			Sky.CLEAR, Temperature.COLD, MealTime.AFTERNOON,
			PageRequest.of(0, 3));

		List<MenuResponse> menuResponses = allByCondition.stream()
			.map(MenuResponse::from)
			.toList();

		System.out.println("menuResponses = " + menuResponses);
	}
}