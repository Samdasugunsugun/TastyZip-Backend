package com.sdsgsg.tastyzipbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sdsgsg.tastyzipbackend.domain.Keyword;
import com.sdsgsg.tastyzipbackend.domain.Review;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
	@Query("select k from Keyword k join fetch k.connects con join fetch con.review r "
		+ "where r = :rev ")
	List<Keyword> findByReview(@Param("rev") Review review);
}
