package com.example.recommendation.domain.menu.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.menu.model.dto.RecommendMenuRequest;

@Component
public class MenuRecommendScheduler {

	private final MenuRecommendService menuRecommendService;
	private final MenuSaveFacade menuSaveService;

	public MenuRecommendScheduler(MenuRecommendService menuRecommendService, MenuSaveFacade menuSaveService) {
		this.menuRecommendService = menuRecommendService;
		this.menuSaveService = menuSaveService;
	}

	@Scheduled()
	public void recommend() {
		List<RecommendMenuRequest> allCombination = RecommendMenuRequest.getAllCombination();

		// List<CompletableFuture<MenuResponse>> menuFutures = new ArrayList<>();

		allCombination.parallelStream()
			.map(request -> {
				MenuDetail menu = menuRecommendService.recommend(request);
				return new MenuResponse(
					menu,
					request.weather(),
					request.mealTime()
				);
			})
			.forEach(menuSaveService::save);

		// for (RecommendMenuRequest request : allCombination) {
		// 	CompletableFuture<MenuResponse> menuFuture = CompletableFuture.supplyAsync(
		// 			() -> menuRecommendService.recommend(request))
		// 		.thenApplyAsync(menuDetail -> new MenuResponse(
		// 			menuDetail,
		// 			request.weather(),
		// 			request.mealTime()
		// 		));
		//
		// 	menuFutures.add(menuFuture);
		// }
		//
		// menuFutures.
	}
}
