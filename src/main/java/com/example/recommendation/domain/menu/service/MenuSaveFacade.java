package com.example.recommendation.domain.menu.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.menu.model.entity.Menu;
import com.example.recommendation.domain.menu.model.entity.MenuRecommend;
import com.example.recommendation.domain.menu.repository.MenuRecommendRepository;
import com.example.recommendation.domain.menu.repository.MenuRepository;

@Component
@Transactional
public class MenuSaveFacade {

	private final MenuRepository menuRepository;
	private final MenuRecommendRepository menuRecommendRepository;

	public MenuSaveFacade(MenuRepository menuRepository, MenuRecommendRepository menuRecommendRepository) {
		this.menuRepository = menuRepository;
		this.menuRecommendRepository = menuRecommendRepository;
	}

	public void save(MenuResponse menuResponse) {
		Menu savedMenu = saveMenu(menuResponse);

		MenuRecommend savedMenuRecommend = menuRecommendRepository.save(getMenuRecommend(menuResponse, savedMenu));

		savedMenuRecommend.recommend();
	}

	private MenuRecommend getMenuRecommend(MenuResponse menuResponse, Menu savedMenu) {
		Optional<MenuRecommend> optionalMenuRecommend = menuRecommendRepository.findByMenuAndCondition(
			savedMenu,
			menuResponse.getSky(),
			menuResponse.getTemperature(),
			menuResponse.mealTime()
		);

		MenuRecommend menuRecommend;

		if (optionalMenuRecommend.isEmpty()) {
			menuRecommend = menuResponse.toMenuRecommend(savedMenu);
		} else {
			menuRecommend = optionalMenuRecommend.get();
		}

		return menuRecommend;
	}

	private Menu saveMenu(MenuResponse menuResponse) {
		Optional<Menu> optionalMenu = menuRepository.findByName(menuResponse.getMenuName());

		if (optionalMenu.isEmpty()) {
			return menuRepository.save(menuResponse.toMenu());
		}

		return optionalMenu.get();
	}
}
