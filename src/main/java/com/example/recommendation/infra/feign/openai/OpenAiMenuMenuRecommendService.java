package com.example.recommendation.infra.feign.openai;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.RecommendMenuRequest;
import com.example.recommendation.domain.menu.service.MenuRecommendService;
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
	public MenuDetail recommend(RecommendMenuRequest recommendMenuRequest) {
		OpenAiChatCallResponse call = openAiChatClient.call(OpenAiChatCallRequest.from(recommendMenuRequest));

		return parseContent(call.getContent());
	}

	private MenuDetail parseContent(String content) {
		try {
			return objectMapper.readValue(content, MenuDetail.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
