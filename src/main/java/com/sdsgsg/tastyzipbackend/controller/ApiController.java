package com.sdsgsg.tastyzipbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiController {
	private final RestaurantService restaurantService;

	private final ReviewService reviewService;

	@GetMapping("/search")
	public ResponseEntity<List<RestaurantDetailsDto>> getSearchResult(@RequestParam String query) {
		List<RestaurantDetailsDto> searchResults = restaurantService.searchRestaurants(query);
		return ResponseEntity.ok(searchResults);
	}

	@GetMapping("/restaurants/{id}")
	public ResponseEntity<RestaurantDetailsDto> getRestaurantInfo(@PathVariable("id") Long id) {
		RestaurantDetailsDto restaurantInfo = restaurantService.getRestaurantDetailsDto(id);
		return ResponseEntity.ok(restaurantInfo);
	}

	@GetMapping("/restaurants/{id}/reviews")
	public ResponseEntity<List<ReviewDto>> getReviewList(@PathVariable("id") Long id) {
		List<ReviewDto> reviewList = reviewService.getReviewDtos(id);
		return ResponseEntity.ok(reviewList);
	}
}
