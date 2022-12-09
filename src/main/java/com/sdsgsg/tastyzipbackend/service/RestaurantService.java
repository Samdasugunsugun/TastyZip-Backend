package com.sdsgsg.tastyzipbackend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;
import com.sdsgsg.tastyzipbackend.dto.RestaurantDetailsDto;
import com.sdsgsg.tastyzipbackend.repository.RestaurantRepository;
import com.sdsgsg.tastyzipbackend.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;
	private final ReviewRepository reviewRepository;

	public List<RestaurantDetailsDto> searchResult(String searchKeyword) {
		List<Restaurant> restaurants = restaurantRepository.findAllRestaurantReviews();

		return null;
	}

	public RestaurantDetailsDto getRestaurantDetailsDto(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("id에 해당하는 음식점이 없습니다"));

		return RestaurantDetailsDto.fromEntity(restaurant);
	}
}
