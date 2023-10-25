package com.example.recommendation.infra.feign.openai;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.recommendation.domain.menu.model.MealTime;
import com.example.recommendation.domain.menu.model.dto.FindMenuRequest;
import com.example.recommendation.domain.menu.model.dto.MenuDetail;
import com.example.recommendation.domain.menu.model.dto.MenuResponse;
import com.example.recommendation.domain.weather.model.SkyStatus;
import com.example.recommendation.domain.weather.model.TemperatureStatus;
import com.example.recommendation.domain.weather.model.Weather;
import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallRequest;
import com.example.recommendation.infra.feign.openai.dto.OpenAiChatCallResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class OpenAiMenuRecommendFacadeServiceTest {

	@Mock
	private OpenAiChatClient openAiChatClient;

	private final ObjectMapper objectMapper = new ObjectMapper();
	@InjectMocks
	private OpenAiMenuMenuRecommendService recommendService;

	@Test
	void recommend() throws JsonProcessingException {
		// given
		String content = "[\n  {\"menu\": \"차돌된장찌개\", \"reason\": \"추운 날씨에는 따뜻한 차돌된장찌개가 어울려요.\"},\n  {\"menu\": \"쌀국수\", \"reason\": \"비오는 날에는 가벼운 쌀국수가 좋아요.\"},\n  {\"menu\": \"라면\", \"reason\": \"추운 오후 한 그릇의 라면은 최고의 선택이에요.\"},\n  {\"menu\": \"떡볶이\", \"reason\": \"시원한 날씨와 비 오는 날에는 매콤하고 따뜻한 떡볶이가 딱이죠.\"},\n  {\"menu\": \"카레라이스\", \"reason\": \"추운 날씨와 비 오는 날에는 따뜻한 카레라이스를 즐겨보세요.\"},\n  {\"menu\": \"우동\", \"reason\": \"추운 날씨와 비 오는 날에는 따뜻한 우동이 딱입니다.\"},\n  {\"menu\": \"김밥\", \"reason\": \"추운 날씨에는 따뜻한 김밥을 먹으면 더 편해요.\"},\n  {\"menu\": \"칼국수\", \"reason\": \"추운 날씨에는 따뜻한 칼국수가 어울려요.\"},\n  {\"menu\": \"팥빙수\", \"reason\": \"비오는 날에는 매콤한 음식 대신 시원한 팥빙수를 즐기세요.\"},\n  {\"menu\": \"돈까스\", \"reason\": \"추운 날씨에는 따뜻하고 바삭한 돈까스가 딱이에요.\"}\n]";
		FindMenuRequest findMenuRequest = new FindMenuRequest(
			TemperatureStatus.COOL,
			SkyStatus.CLEAR,
			MealTime.EVENING
		);
		OpenAiChatCallResponse.forTest(content);
		BDDMockito.when(openAiChatClient.call(any(OpenAiChatCallRequest.class)))
			.thenReturn(OpenAiChatCallResponse.forTest(content));

		MenuResponse expect = new MenuResponse(
			objectMapper.readValue(content, new TypeReference<List<MenuDetail>>() {
			}),
			new Weather(findMenuRequest.skyStatus(), findMenuRequest.temperature()),
			findMenuRequest.mealTime()
		);

		// when
		MenuResponse actual = recommendService.recommend(findMenuRequest);

		// then
		verify(openAiChatClient).call(any());
		assertThat(actual).isEqualTo(expect);

	}
}