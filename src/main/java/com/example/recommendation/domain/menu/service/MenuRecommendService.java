package com.example.recommendation.domain.menu.service;

import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.RecommendMenuRequest;

public interface MenuRecommendService {

	MenuDetail recommend(RecommendMenuRequest request);
}
