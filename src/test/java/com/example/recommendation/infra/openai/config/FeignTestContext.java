package com.example.recommendation.infra.openai.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import com.example.recommendation.infra.feign.FeignConfig;

@ImportAutoConfiguration({
	FeignConfig.class,
	FeignAutoConfiguration.class,
	HttpMessageConvertersAutoConfiguration.class
})
public class FeignTestContext {
}
