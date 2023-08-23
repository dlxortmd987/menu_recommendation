package com.example.recommendation.infra.feign.openai;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallRequest;
import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallResponse;

@SpringBootTest
class OpenAiChatClientTest {

	@Autowired
	private OpenAiChatClient openAiChatClient;

	@Disabled
	@DisplayName("OpenAi api를 호출할 수 있다.")
	@Test
	void call() {
		OpenAiChatCallResponse response = openAiChatClient.call(OpenAiChatCallRequest.newInstance("흐린 날 메뉴 추천해줘"));

		System.out.println(response.choices().get(0).message().content());
	}
}