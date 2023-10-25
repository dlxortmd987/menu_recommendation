package com.example.recommendation.infra.csv;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recommendation.domain.recommend.model.Coordinate;
import com.example.recommendation.domain.weather.AddressService;
import com.example.recommendation.infra.csv.dto.CsvResponse;

@Service
public class AddressCsvService implements AddressService {

	private static final int DO_INDEX = 2;
	private static final int GU_INDEX = 3;
	private static final int DONG_INDEX = 4;

	private static final int LATITUDE_INDEX = 13;
	private static final int LONGITUDE_INDEX = 14;

	private static final int FLOOR_THRESHOLD = 1000000;

	private static boolean isCoordinateMatch(Coordinate coordinate, List<String> line) {
		Double csvLatitude = getSlicedTokenFromCsvLine(line, LATITUDE_INDEX);
		Double csvLongitude = getSlicedTokenFromCsvLine(line, LONGITUDE_INDEX);

		return coordinate.isSame(csvLatitude, csvLongitude);
	}

	private static double getSlicedTokenFromCsvLine(List<String> csvLine, int index) {
		return Math.floor(Double.parseDouble(csvLine.get(index)) * FLOOR_THRESHOLD) / FLOOR_THRESHOLD;
	}

	@Override
	public String get(Coordinate coordinate) {
		List<CsvResponse> csvResponses = OpenCsvReadService.readWithoutHeader("weather/coordinate.csv");

		return csvResponses.stream()
			.filter(csvResponse -> isCoordinateMatch(coordinate, csvResponse.line()))
			.findFirst()
			.map(csvResponse -> {
				List<String> csvLine = csvResponse.line();
				return "%s %s %s".formatted(csvLine.get(DO_INDEX), csvLine.get(GU_INDEX), csvLine.get(DONG_INDEX));
			})
			.orElseThrow();
	}
}
