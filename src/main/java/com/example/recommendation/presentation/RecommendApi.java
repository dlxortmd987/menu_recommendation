package com.example.recommendation.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recommendation.domain.recommend.RecommendFacadeService;
import com.example.recommendation.domain.recommend.model.dto.RecommendResponse;
import com.example.recommendation.presentation.dto.RecommendRequest;

@RestController
@RequestMapping("/recommend")
public class RecommendApi {

	private final RecommendFacadeService recommendFacadeService;

	public RecommendApi(RecommendFacadeService recommendFacadeService) {
		this.recommendFacadeService = recommendFacadeService;
	}

	@PostMapping
	@CrossOrigin
	public ResponseEntity<RecommendResponse> recommend(
		@RequestBody RecommendRequest recommendRequest
	) {
		return ResponseEntity.ok(recommendFacadeService.recommend(recommendRequest.coordinate(),
			recommendRequest.targetTime()));
	}
}
