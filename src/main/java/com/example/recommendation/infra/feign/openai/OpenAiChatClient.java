package com.example.recommendation.infra.feign.openai;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallRequest;
import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallResponse;

@FeignClient(name = "OpenAiChat", url = "${openai.chat-url}", configuration = OpenAiHeaderConfig.class)
public interface OpenAiChatClient {

	@PostMapping(value = "/v1/chat/completions", consumes = "application/json")
	OpenAiChatCallResponse call(
		@RequestBody OpenAiChatCallRequest request
	);
}
