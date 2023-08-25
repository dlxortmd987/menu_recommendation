package com.example.recommendation.domain.recommend;

import com.example.recommendation.domain.menu.model.dto.MenuRequest;
import com.example.recommendation.domain.recommend.dto.MenuResponse;

public interface MenuRecommendService {

	MenuResponse recommend(MenuRequest request);
}
