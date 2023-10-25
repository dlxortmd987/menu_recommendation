package com.example.recommendation.domain.menu;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;

@Service
public class MenuService {

	private final MenuRecommendService menuRecommendService;

	public MenuService(MenuRecommendService menuRecommendService) {
		this.menuRecommendService = menuRecommendService;
	}

	public MenuResponse findMenuList(FindMenuRequest menuRequest) {
		return menuRecommendService.recommend(menuRequest);
	}
}
