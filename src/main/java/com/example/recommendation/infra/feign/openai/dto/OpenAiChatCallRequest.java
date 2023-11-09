package com.example.recommendation.infra.feign.openai.dto;

import java.util.List;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.menu.model.dto.RecommendMenuRequest;
import com.example.recommendation.domain.weather.model.Sky;
import com.example.recommendation.domain.weather.model.Temperature;

public record OpenAiChatCallRequest(
	String model,
	ResponseFormat response_format,
	List<Message> messages
) {
	private static final Message SYSTEM_GUIDE_MESSAGE = new Message("system",
		""" 
			 		당신은 날씨와 시간, 하늘 상태를 기반으로 메뉴를 추천해주는 역할을 수행합니다.
			 		
			 		메뉴를 추천할 때 이유를 5단어 이내로 같이 보여줍니다.
			 		
			 		이유를 작성할 때 친절한 20대 여성 말투로 대답합니다.
			 		
			 		메뉴 이름은 다음과 같이 수식어를 붙이지 않습니다. 우동, 라멘, 등등
			 		
			 		메뉴는 하나만 추천합니다.
			 		
			 		json 형식으로 응답합니다.
			""");
	private static final String MODEL = "gpt-3.5-turbo-1106";
	private static final String USER_ROLE = "user";
	private static final Message USER_GUIDE_MESSAGE = new Message("user",
		"다음 상황과 어울리는 메뉴 추천해줘.(날씨: %s, 하늘: %s, 시간: %s)".formatted(Temperature.COLD, Sky.CLEAR, MealTime.EVENING));
	private static final Message ASSISTANT_GUIDE_MESSAGE = new Message("assistant",
		"""
							{"name": "김치찌개", "reason": "추운 날에는 속을 풀어줘요~"}
			""");
	private static final String USER_MESSAGE_FORMAT = "다음 상황과 어울리는 메뉴 추천해줘.(날씨: %s, 하늘: %s, 시간: %s)";

	public OpenAiChatCallRequest(String message) {
		this(MODEL, new ResponseFormat("json_object"),
			List.of(SYSTEM_GUIDE_MESSAGE, USER_GUIDE_MESSAGE, ASSISTANT_GUIDE_MESSAGE,
				new Message(USER_ROLE, message)));
	}

	public static OpenAiChatCallRequest from(RecommendMenuRequest request) {
		return new OpenAiChatCallRequest(buildMessage(request));
	}

	private static String buildMessage(RecommendMenuRequest request) {
		return USER_MESSAGE_FORMAT.formatted(
			request.weather().temperature(),
			request.weather().sky(),
			request.mealTime()
		);
	}

	public record Message(
		String role,
		String content
	) {
	}

	public record ResponseFormat(
		String type
	) {
	}

}
