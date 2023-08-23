package com.example.recommendation.infra.feign.weather;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weather")
public class WeatherProperties {

	private String key;
	private String dataType;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
