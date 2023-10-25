package com.example.recommendation.infra.feign.openai;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.MenuRecommendService;
import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.weather.model.Weather;
import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallRequest;
import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenAiMenuMenuRecommendService implements MenuRecommendService {

	private final OpenAiChatClient openAiChatClient;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public OpenAiMenuMenuRecommendService(OpenAiChatClient openAiChatClient) {
		this.openAiChatClient = openAiChatClient;
	}

	@Override
	public MenuResponse recommend(FindMenuRequest findMenuRequest) {
		OpenAiChatCallResponse openAiResponse = openAiChatClient.call(OpenAiChatCallRequest.from(findMenuRequest));

		MenuDetail menuDetails = parseContent(openAiResponse.getContent());

		return new MenuResponse(
			List.of(menuDetails),
			new Weather(findMenuRequest.skyStatus(), findMenuRequest.temperature()),
			findMenuRequest.mealTime()
		);
	}

	private MenuDetail parseContent(String content) {
		try {
			return objectMapper.readValue(content, MenuDetail.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
