package com.example.recommendation.infra.feign.naver.search;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class NaverSearchHeaderConfig {

	private static final String DEFAULT_VIEW_SIZE = "5";
	private static final String ACCURACY_SIZE_SORT = "random";

	@Bean
	public RequestInterceptor requestInterceptor(
		@Value("${naver.search.client-id}") String clientId,
		@Value("${naver.search.client-secret}") String clientSecret
	) {
		return restTemplate -> restTemplate
			.header("X-Naver-Client-Id", clientId)
			.header("X-Naver-Client-Secret", clientSecret)
			.header("Content-Type", "application/json")
			.query("display", DEFAULT_VIEW_SIZE)
			.query("sort", ACCURACY_SIZE_SORT);
	}
}
