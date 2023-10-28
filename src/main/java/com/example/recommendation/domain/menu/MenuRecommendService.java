package com.example.recommendation.domain.menu;

import java.util.List;

import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;

public interface MenuRecommendService {

	List<MenuResponse> recommend(FindMenuRequest request);
}
