package com.example.recommendation.infra.feign.openai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class OpenAiHeaderConfig {

	@Bean
	public RequestInterceptor requestInterceptor(
		@Value("${openai.key}")
		String key
	) {
		return requestTemplate -> requestTemplate
			.header("Authorization", key);
	}
}
