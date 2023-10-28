package com.example.recommendation.infra.feign.naver.address;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recommendation.infra.feign.naver.address.dto.NaverAddressClientResponse;

@FeignClient(name = "NaverAddress", url = "${naver.address.url}", configuration = NaverAddressConfig.class)
public interface NaverAddressClient {

	@GetMapping("/v2/gc")
	NaverAddressClientResponse call(@RequestParam(value = "coords") String coordinate);
}
