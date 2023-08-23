package com.example.recommendation.infra.feign.openai.dto;

public record OpenAiChoice(
	Message message
) {

	public record Message(
		String content
	) {
	}
}
