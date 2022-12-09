package com.sdsgsg.tastyzipbackend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;
import com.sdsgsg.tastyzipbackend.domain.Review;
import com.sdsgsg.tastyzipbackend.dto.ReviewDto;
import com.sdsgsg.tastyzipbackend.repository.RestaurantRepository;
import com.sdsgsg.tastyzipbackend.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final RestaurantRepository restaurantRepository;

	public List<ReviewDto> getReviewDtos(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("id에 해당하는 음식점이 없습니다"));

		List<Review> reviews = reviewRepository.findByRestaurant(restaurant);

		return reviews.stream()
			.map(ReviewDto::fromEntity)
			.collect(Collectors.toList());
	}
}
