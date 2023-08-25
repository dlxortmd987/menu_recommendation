package com.example.recommendation.infra.feign.naver;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.recommendation.domain.restaurant.dto.RestaurantRequest;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponseDetail;
import com.example.recommendation.infra.feign.naver.dto.NaverSearchItem;
import com.example.recommendation.infra.feign.naver.dto.NaverSearchResponse;

@ExtendWith(MockitoExtension.class)
class NaverRestaurantSearchServiceTest {

	@Mock
	private NaverSearchClient naverSearchClient;

	@InjectMocks
	private NaverRestaurantSearchService naverRestaurantSearchService;

	private static NaverSearchResponse getTestClientResponse() {
		return new NaverSearchResponse(LocalDateTime.now(), List.of(new NaverSearchItem(
			"저지클럽",
			"https://www.instagram.com/jerseyclub.kr/",
			"음식점>햄버거",
			"",
			"",
			"경기도 고양시 일산동구 마두동 851-4 101호",
			"경기도 고양시 일산동구 일산로330번길 3 101호",
			"1267843346",
			"376650034"
		)));
	}

	@Test
	void search() {
		// given
		String menu = "국밥";
		String location = "마두2동";

		NaverSearchResponse clientTestResponse = getTestClientResponse();
		RestaurantResponse expect = getExpectRestaurantResponse(clientTestResponse);

		BDDMockito.when(naverSearchClient.call(anyString()))
			.thenReturn(clientTestResponse);

		// when
		RestaurantResponse actual = naverRestaurantSearchService.search(new RestaurantRequest(menu, location));

		// then
		verify(naverSearchClient).call(anyString());
		assertThat(actual).isEqualTo(expect);
	}

	private RestaurantResponse getExpectRestaurantResponse(NaverSearchResponse clientTestResponse) {
		List<RestaurantResponseDetail> restaurantResponseDetails = clientTestResponse.items()
			.stream()
			.map(item -> new RestaurantResponseDetail(
				item.title(),
				item.link(),
				item.category(),
				item.description(),
				item.telephone(),
				item.address()))
			.toList();
		return new RestaurantResponse(restaurantResponseDetails, clientTestResponse.lastBuildDate());
	}
}