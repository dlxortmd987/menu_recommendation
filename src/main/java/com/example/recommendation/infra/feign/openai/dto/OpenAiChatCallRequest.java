package com.example.recommendation.infra.feign.openai.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public record OpenAiChatCallRequest(
	String model,
	List<Message> messages
) {

	private static final Message SYSTEM_GUIDE_MESSAGE = new Message("system",
		"당신은 날씨와 시간을 기반으로 메뉴를 추천해주는 영양사입니다. "
			+ "메뉴를 추천할 때 이유를 짧게 첨부합니다. 메뉴는 수식어를 붙이지 않고 메뉴 이름만 작성합니다."
			+ "메뉴는 하나만 추천합니다."
			+ "무조건 json 형식으로 응답합니다."
			+ "이 때 json 형식은 다음과 같습니다."
			+ """
				{"menu": string, "reason": string}
			""");
	private static final String MODEL = "gpt-3.5-turbo";
	private static final String USER_ROLE = "user";
	private static final Message ASSISTANT_GUIDE_MESSAGE = new Message("assistant",
		"""
							{"menu": "김치찌개", "reason": "추운날 속을 풀어준다."}
			""");
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static final String USER_MESSAGE_FORMAT = "다음 상황과 어울리는 메뉴 추천해줘.(%s, %s, %s)";

	public OpenAiChatCallRequest(String message) {
		this(MODEL, List.of(SYSTEM_GUIDE_MESSAGE, ASSISTANT_GUIDE_MESSAGE, new Message(USER_ROLE, message)));
	}

	public static OpenAiChatCallRequest from(FindMenuRequest request) {
		return new OpenAiChatCallRequest(buildMessage(request));
	}

	private static String buildMessage(FindMenuRequest request) {
		return USER_MESSAGE_FORMAT.formatted(
			request.temperature(),
			request.skyStatus(),
			request.mealTime()
		);
	}

	public record Message(
		String role,
		String content
	) {
	}
}
