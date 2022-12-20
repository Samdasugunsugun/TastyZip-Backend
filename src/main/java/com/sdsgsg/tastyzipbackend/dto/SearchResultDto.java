package com.sdsgsg.tastyzipbackend.dto;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchResultDto {
	private Restaurant restaurant;
	private Long count;

	public SearchResultDto(Restaurant res, Long count) {
		this.restaurant = res;
		this.count = count;
	}
}
