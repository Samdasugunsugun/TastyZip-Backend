package com.sdsgsg.tastyzipbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;
import com.sdsgsg.tastyzipbackend.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	public List<Review> findByRestaurant(Restaurant restaurant);
}
