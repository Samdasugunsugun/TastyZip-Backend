package com.sdsgsg.tastyzipbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	@Query("select r from Restaurant r join fetch r.reviews ")
	List<Restaurant> findAllRestaurantReviews();
}
