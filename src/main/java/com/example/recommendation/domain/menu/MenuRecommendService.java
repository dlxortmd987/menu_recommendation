package com.example.recommendation.domain.menu;

import com.example.recommendation.domain.menu.model.dto.MenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;

public interface MenuRecommendService {

	MenuResponse recommend(MenuRequest request);
}
