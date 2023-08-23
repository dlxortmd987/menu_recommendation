package com.example.recommendation.infra.csv;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.example.recommendation.infra.csv.dto.CsvResponse;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class OpenCsvReadService {

	public static List<CsvResponse> readWithoutHeader(String fileName) throws IOException, CsvException {
		CSVReader csvReader = new CSVReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()));
		return CsvResponse.from(csvReader.readAll());
	}
}
