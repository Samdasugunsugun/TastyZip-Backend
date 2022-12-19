package com.sdsgsg.tastyzipbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
