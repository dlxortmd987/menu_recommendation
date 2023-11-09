package com.example.recommendation.domain.menu.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.model.dto.MenuFindConditionRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.menu.repository.MenuRecommendRepository;

@Service
public class MenuFindService {

	private final MenuRecommendRepository menuRecommendRepository;

	public MenuFindService(MenuRecommendRepository menuRecommendRepository) {
		this.menuRecommendRepository = menuRecommendRepository;
	}

	public List<MenuResponse> findAllByCondition(MenuFindConditionRequest condition, int findNumber) {
		return menuRecommendRepository.findAllByCondition(
				condition.sky(),
				condition.temperature(),
				condition.mealTime(),
				PageRequest.of(0, findNumber)
			).stream()
			.map(MenuResponse::from)
			.toList();
	}
}
