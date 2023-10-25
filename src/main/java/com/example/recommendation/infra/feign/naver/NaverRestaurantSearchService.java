package com.example.recommendation.infra.feign.naver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.restaurant.RestaurantSearchService;
import com.example.recommendation.domain.restaurant.dto.RestaurantRequest;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponse;
import com.example.recommendation.domain.restaurant.dto.RestaurantResponseDetail;
import com.example.recommendation.infra.feign.naver.dto.NaverSearchResponse;

@Service
public class NaverRestaurantSearchService implements RestaurantSearchService {

	private static final String QUERY_FORMAT = "%s %s 맛집";
	private final NaverSearchClient naverSearchClient;

	public NaverRestaurantSearchService(NaverSearchClient naverSearchClient) {
		this.naverSearchClient = naverSearchClient;
	}

	@Override
	public RestaurantResponse search(RestaurantRequest request) {
		NaverSearchResponse naverSearchResponse = naverSearchClient.call(buildQuery(request));
		return convertResponse(naverSearchResponse);
	}

	private RestaurantResponse convertResponse(NaverSearchResponse naverSearchResponse) {
		List<RestaurantResponseDetail> restaurantResponseDetails = naverSearchResponse.items()
			.stream()
			.map(item -> new RestaurantResponseDetail(
				item.title().replaceAll("<[^>]*>", ""),
				item.link(),
				item.category(),
				item.description(),
				item.telephone(),
				item.address()))
			.toList();
		return new RestaurantResponse(restaurantResponseDetails, naverSearchResponse.lastBuildDate());
	}

	private String buildQuery(RestaurantRequest request) {
		return QUERY_FORMAT.formatted(request.address(), request.menu());
	}
}
