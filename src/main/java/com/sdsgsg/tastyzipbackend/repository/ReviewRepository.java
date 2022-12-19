package com.sdsgsg.tastyzipbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;
import com.sdsgsg.tastyzipbackend.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByRestaurant(Restaurant restaurant);

	@Query("select distinct r from Review r join fetch r.connects c join fetch c.keyword k where k.keyword = :kw ")
	List<Review> findReviewsByKeyword(@Param("kw") String keyword);
}
