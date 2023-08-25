package com.example.recommendation.infra.feign.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recommendation.infra.feign.naver.dto.NaverSearchResponse;

@FeignClient(name = "NaverSearch", url = "${naver.url}", configuration = NaverHeaderConfig.class)
public interface NaverSearchClient {

	@GetMapping("/v1/search/local.json")
	NaverSearchResponse call(@RequestParam(value = "query") String query);
}
