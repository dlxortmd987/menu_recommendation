package com.example.recommendation.infra.feign.naver.search.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record NaverSearchResponse(

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DESERIALIZE_FORMAT, locale = "en_GB")
	LocalDateTime lastBuildDate,

	List<NaverSearchItem> items
) {
	private static final String DESERIALIZE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

}
