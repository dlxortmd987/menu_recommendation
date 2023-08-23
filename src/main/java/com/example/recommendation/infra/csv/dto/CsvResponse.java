package com.example.recommendation.infra.csv.dto;

import java.util.Arrays;
import java.util.List;

public record CsvResponse(
	List<String> line
) {

	public static List<CsvResponse> from(List<String[]> lines) {
		return lines.stream()
			.skip(1L)
			.map(Arrays::asList)
			.map(CsvResponse::new)
			.toList();
	}
}
