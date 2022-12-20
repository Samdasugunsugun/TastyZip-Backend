package com.sdsgsg.tastyzipbackend.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.sdsgsg.tastyzipbackend.dto.SearchResultDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchQueryRepository {
	private final EntityManager em;

	public List<SearchResultDto> findByKeywords(List<String> keyword) {
		return em.createQuery(
				"select new com.sdsgsg.tastyzipbackend.dto.SearchResultDto(rev.restaurant, count(k.keyword))"
					+ " from Review rev join rev.connects c join c.keyword k"
					+ " where k.keyword in :kw"
					+ " group by rev.restaurant ", SearchResultDto.class)
			.setParameter("kw", keyword)
			.getResultList();
	}
}
