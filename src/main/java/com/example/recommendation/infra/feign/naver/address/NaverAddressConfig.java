package com.example.recommendation.infra.feign.naver.address;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class NaverAddressConfig {

	@Bean
	public RequestInterceptor requestInterceptor(
		@Value("${naver.address.client-id}") String clientId,
		@Value("${naver.address.client-secret}") String clientSecret
	) {
		return restTemplate -> restTemplate
			.header("X-NCP-APIGW-API-KEY-ID", clientId)
			.header("X-NCP-APIGW-API-KEY", clientSecret)
			.header("Content-Type", "application/json")
			.query("orders", "admcode")
			.query("output", "json");
	}
}
