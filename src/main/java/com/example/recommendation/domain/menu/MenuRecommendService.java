package com.example.recommendation.domain.menu;

import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;

public interface MenuRecommendService {

	MenuResponse recommend(FindMenuRequest request);
}
