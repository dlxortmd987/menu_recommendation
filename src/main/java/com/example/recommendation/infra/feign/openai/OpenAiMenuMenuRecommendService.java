package com.example.recommendation.infra.feign.openai;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.MenuRecommendService;
import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.weather.model.Weather;
import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenAiMenuMenuRecommendService implements MenuRecommendService {

	private final OpenAiChatClient openAiChatClient;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public OpenAiMenuMenuRecommendService(OpenAiChatClient openAiChatClient) {
		this.openAiChatClient = openAiChatClient;
	}

	@Override
	public List<MenuResponse> recommend(FindMenuRequest findMenuRequest) {
		List<CompletableFuture<MenuResponse>> futures = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			CompletableFuture<MenuResponse> future = CompletableFuture.supplyAsync(
					() -> openAiChatClient.call(OpenAiChatCallRequest.from(findMenuRequest)))
				.thenApply(openAiResponse -> new MenuResponse(
					List.of(parseContent(openAiResponse.getContent())),
					new Weather(findMenuRequest.skyStatus(), findMenuRequest.temperature()),
					findMenuRequest.mealTime()
				));

			futures.add(future);
		}

		// OpenAiChatCallResponse openAiResponse = openAiChatClient.call(OpenAiChatCallRequest.from(findMenuRequest));

		// MenuDetail menuDetails = parseContent(openAiResponse.getContent());

		// return new MenuResponse(
		// 	List.of(menuDetails),
		// 	new Weather(findMenuRequest.skyStatus(), findMenuRequest.temperature()),
		// 	findMenuRequest.mealTime()
		// );

		return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
			.thenApply(o -> futures.stream()
				.map(CompletableFuture::join)
				.toList())
			.join();
	}

	private MenuDetail parseContent(String content) {
		try {
			return objectMapper.readValue(content, MenuDetail.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
