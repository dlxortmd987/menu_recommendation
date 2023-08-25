package com.example.recommendation.infra.feign.openai;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recommendation.domain.weather.menu.model.TimeSlot;
import com.example.recommendation.domain.weather.menu.model.dto.MenuRequest;
import com.example.recommendation.domain.weather.model.SkyStatus;
import com.example.recommendation.domain.weather.model.TemperatureStatus;
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
		MenuRequest request = new MenuRequest(
			TemperatureStatus.COLD,
			SkyStatus.RAINY,
			TimeSlot.AFTERNOON
		);

		OpenAiChatCallResponse response = openAiChatClient.call(OpenAiChatCallRequest.from(request));

		System.out.println(response.choices().get(0).message().content());
	}
}