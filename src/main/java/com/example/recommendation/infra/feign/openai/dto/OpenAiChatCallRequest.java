package com.example.recommendation.infra.feign.openai.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;

public record OpenAiChatCallRequest(
	String model,
	List<Message> messages
) {

	private static final String MODEL = "gpt-3.5-turbo";
	private static final String USER_ROLE = "user";

	// private static final Message SYSTEM_GUIDE_MESSAGE = new Message("system",
	// 	"너는 날씨와 시간에 따른 메뉴를 20자 이하의 간단한 이유와 함께 추천해주는 역할이야. 메뉴는 3개를 추천해줘, json 형식으로 답을 내려줄 수 있어.");
	private static final Message SYSTEM_GUIDE_MESSAGE = new Message("system",
		"당신은 날씨와 시간을 기반으로 메뉴를 추천해주는 영양사입니다. "
			+ "메뉴를 추천할 때 이유를 짧게 첨부합니다. 메뉴는 수식어를 붙이지 않고 메뉴 이름만 작성합니다."
			+ "이유는 띄어쓰기를 제외하고 20자를 넘지 않습니다. "
			+ "json 형식으로 대답합니다.");
	private static final Message ASSISTANT_GUIDE_MESSAGE = new Message("assistant",
		"{menu: 김치찌개, reason: 추운날 속을 풀어준다.}");
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
			request.timeSlot()
		);
	}

	public record Message(
		String role,
		String content
	) {
	}
}
