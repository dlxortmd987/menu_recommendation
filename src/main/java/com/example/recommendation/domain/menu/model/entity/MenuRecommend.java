package com.example.recommendation.domain.menu.model.entity;

import com.example.recommendation.domain.global.BaseEntity;
import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MenuRecommend extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Menu menu;

	@Column
	private String recommendReason;

	@Enumerated(EnumType.STRING)
	private Temperature temperature;

	@Enumerated(EnumType.STRING)
	private Sky sky;

	@Enumerated(EnumType.STRING)
	private MealTime mealTime;

	private int recommendCount;

	protected MenuRecommend() {
	}

	public MenuRecommend(
		Menu menu,
		String recommendReason,
		Temperature temperature,
		Sky sky,
		MealTime mealTime
	) {
		this.menu = menu;
		this.recommendReason = recommendReason;
		this.temperature = temperature;
		this.sky = sky;
		this.mealTime = mealTime;
		this.recommendCount = 0;
	}

	public void recommend() {
		recommendCount++;
	}
}
