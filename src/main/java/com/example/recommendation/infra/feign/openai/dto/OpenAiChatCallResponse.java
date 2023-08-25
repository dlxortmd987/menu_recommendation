package com.example.recommendation.infra.feign.openai.dto;

import java.util.List;

public record OpenAiChatCallResponse(
	List<OpenAiChoice> choices
) {

	public static OpenAiChatCallResponse forTest(String content) {
		return new OpenAiChatCallResponse(List.of(new OpenAiChoice(
			new OpenAiChoice.Message(content)
		)));
	}

	public String getContent() {
		return choices().get(0)
			.message()
			.content();
	}
}
