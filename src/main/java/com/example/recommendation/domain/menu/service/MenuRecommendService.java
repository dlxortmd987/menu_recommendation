package com.example.recommendation.domain.menu.service;

import com.example.recommendation.domain.menu.model.dto.MenuRequest;
import com.example.recommendation.domain.menu.service.dto.MenuResponse;

public interface MenuRecommendService {

	MenuResponse recommend(MenuRequest request);
}
