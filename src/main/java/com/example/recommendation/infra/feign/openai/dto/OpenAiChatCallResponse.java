package com.example.recommendation.infra.feign.openai.dto;

import java.util.List;

public record OpenAiChatCallResponse(
	List<OpenAiChoice> choices
) {
}
