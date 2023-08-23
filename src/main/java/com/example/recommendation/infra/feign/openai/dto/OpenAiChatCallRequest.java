package com.example.recommendation.infra.feign.openai.dto;

import java.util.List;

public record OpenAiChatCallRequest(
	String model,
	List<Message> messages
) {

	private static final String MODEL = "gpt-3.5-turbo";
	private static final String USER_ROLE = "user";
	private static final Message SYSTEM_GUIDE_MESSAGE = new Message("system",
		"너는 날씨와 시간에 따른 메뉴를 20자 이하의 간단한 이유와 함께 추천해주는 역할이야. 메뉴는 10개를 추천해줘, json 형식으로 답을 내려줄 수 있어. json 형식 FORMAT: "
			+ "[{menu: 김치찌개, reason: 추운날 속을 풀어준다.}, {menu: , reason: }]");

	public static OpenAiChatCallRequest newInstance(String content) {
		return new OpenAiChatCallRequest(MODEL,
			List.of(
				SYSTEM_GUIDE_MESSAGE,
				new Message(USER_ROLE, content)
			));
	}

	public record Message(
		String role,
		String content
	) {
	}
}
