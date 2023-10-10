package com.example.recommendation.domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;

@Service
public class MenuService {

	private final MenuRecommendService menuRecommendService;

	public MenuService(MenuRecommendService menuRecommendService) {
		this.menuRecommendService = menuRecommendService;
	}

	public List<MenuResponse> findMenuList(List<FindMenuRequest> findMenuRequests) {
		List<MenuResponse> menuResponses = new ArrayList<>();
		for (FindMenuRequest menuRequest : findMenuRequests) {
			menuResponses.add(menuRecommendService.recommend(menuRequest));
		}

		return Collections.unmodifiableList(menuResponses);
	}
}
