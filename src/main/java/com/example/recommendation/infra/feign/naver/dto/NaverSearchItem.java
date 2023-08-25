package com.example.recommendation.infra.feign.naver.dto;

public record NaverSearchItem(
	String title,
	String link,
	String category,
	String description,
	String telephone,
	String address,
	String roadAddress,
	String mapx,
	String mapy
) {
}
