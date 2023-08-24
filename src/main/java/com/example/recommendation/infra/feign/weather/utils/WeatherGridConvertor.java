package com.example.recommendation.infra.feign.weather.utils;

import java.util.List;

import com.example.recommendation.infra.csv.OpenCsvReadService;
import com.example.recommendation.infra.csv.dto.CsvResponse;
import com.example.recommendation.infra.feign.weather.dto.WeatherGrid;

public class WeatherGridConvertor {

	private static final int LATITUDE_INDEX = 13;
	private static final int LONGITUDE_INDEX = 14;

	private static final int X_INDEX = 5;
	private static final int Y_INDEX = 6;

	private static final int FLOOR_THRESHOLD = 1000000;

	public static WeatherGrid get(Double latitude, Double longitude) {
		List<CsvResponse> csvResponses = OpenCsvReadService.readWithoutHeader("weather/coordinate.csv");

		return csvResponses.stream()
			.filter(csvResponse -> isCoordinateMatch(latitude, longitude, csvResponse.line()))
			.findFirst()
			.map(csvResponse -> {
				List<String> csvLine = csvResponse.line();
				return new WeatherGrid(csvLine.get(X_INDEX), csvLine.get(Y_INDEX));
			})
			.orElseThrow();
	}

	private static boolean isCoordinateMatch(Double latitude, Double longitude, List<String> line) {
		Double csvLatitude = getSlicedTokenFromCsvLine(line, LATITUDE_INDEX);
		Double csvLongitude = getSlicedTokenFromCsvLine(line, LONGITUDE_INDEX);

		return isCoordinateSame(latitude, csvLatitude) && isCoordinateSame(longitude, csvLongitude);
	}

	private static boolean isCoordinateSame(Double coordinate, Double csvCoordinate) {
		return coordinate.compareTo(csvCoordinate) == 0;
	}

	private static double getSlicedTokenFromCsvLine(List<String> csvLine, int index) {
		return Math.floor(Double.parseDouble(csvLine.get(index)) * FLOOR_THRESHOLD) / FLOOR_THRESHOLD;
	}
}
