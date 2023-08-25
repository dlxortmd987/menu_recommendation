package com.example.recommendation.domain.weather.menu.service;

import com.example.recommendation.domain.weather.menu.model.dto.MenuRequest;
import com.example.recommendation.domain.weather.menu.service.dto.MenuResponse;

public interface MenuRecommendService {

	MenuResponse recommend(MenuRequest request);
}
