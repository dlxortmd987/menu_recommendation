package com.example.recommendation.infra.feign.naver;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recommendation.infra.feign.naver.dto.NaverSearchResponse;

@SpringBootTest
class NaverSearchClientTest {

	@Autowired
	private NaverSearchClient naverSearchClient;

	@Disabled
	@DisplayName("네이버 API를 통해 식당을 검색할 수 있다.")
	@Test
	void call() {
		NaverSearchResponse response = naverSearchClient.call("마두동 맛집");
		System.out.println("response = " + response);
	}
}