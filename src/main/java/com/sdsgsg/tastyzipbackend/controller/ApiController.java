package com.sdsgsg.tastyzipbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdsgsg.tastyzipbackend.dto.RestaurantDetailsDto;
import com.sdsgsg.tastyzipbackend.dto.ReviewDto;
import com.sdsgsg.tastyzipbackend.service.RestaurantService;
import com.sdsgsg.tastyzipbackend.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {
	private final RestaurantService restaurantService;
	
	private final ReviewService reviewService;

	@GetMapping("/search")
	public ResponseEntity<List<RestaurantDetailsDto>> getSearchResult(@RequestParam String query) {
		List<RestaurantDetailsDto> searchResults = restaurantService.searchResult(query);
		return ResponseEntity.ok(searchResults);
	}

	@GetMapping("/restaurant")
	public ResponseEntity<RestaurantDetailsDto> getRestaurantInfo(@RequestParam Long id) {
		RestaurantDetailsDto restaurantInfo = restaurantService.getRestaurantDetailsDto(id);
		return ResponseEntity.ok(restaurantInfo);
	}

	@GetMapping("/review")
	public ResponseEntity<List<ReviewDto>> getReviewList(@RequestParam Long id) {
		List<ReviewDto> reviewList = reviewService.getReviewDtos(id);
		return ResponseEntity.ok(reviewList);
	}
}
