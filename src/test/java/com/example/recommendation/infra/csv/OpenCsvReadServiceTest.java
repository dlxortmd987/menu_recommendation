package com.example.recommendation.infra.csv;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.recommendation.infra.csv.dto.CsvResponse;
import com.opencsv.exceptions.CsvException;

class OpenCsvReadServiceTest {

	@DisplayName("OpenCsv를 통해 csv 파일을 읽을 수 있다.")
	@Test
	void read() throws CsvException, IOException {
		List<CsvResponse> read = OpenCsvReadService.readWithoutHeader("weather/coordinate.csv");
		assertThat(read.size()).isEqualTo(5);
	}
}