package com.example.recommendation.infra.feign.openai.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.dto.MenuRequest;

public record OpenAiChatCallRequest(
	String model,
	List<Message> messages
) {

	private static final String MODEL = "gpt-3.5-turbo";
	private static final String USER_ROLE = "user";

	private static final Message SYSTEM_GUIDE_MESSAGE = new Message("system",
		"너는 날씨와 시간에 따른 메뉴를 20자 이하의 간단한 이유와 함께 추천해주는 역할이야. 메뉴는 10개를 추천해줘, json 형식으로 답을 내려줄 수 있어. json 형식 FORMAT: "
			+ "[{menu: 김치찌개, reason: 추운날 속을 풀어준다.}, {menu: , reason: }]");
	private static final String USER_MESSAGE_FORMAT = "다음 상황과 어울리는 메뉴 추천해줘.(%s, %s, %s)";

	public OpenAiChatCallRequest(String message) {
		this(MODEL, List.of(SYSTEM_GUIDE_MESSAGE, new Message(USER_ROLE, message)));
	}

	public static OpenAiChatCallRequest from(MenuRequest request) {
		return new OpenAiChatCallRequest(buildMessage(request));
	}

	private static String buildMessage(MenuRequest request) {
		return USER_MESSAGE_FORMAT.formatted(
			request.temperature(),
			request.skyStatus(),
			request.timeSlot()
		);
	}

	public record Message(
		String role,
		String content
	) {
	}
}
