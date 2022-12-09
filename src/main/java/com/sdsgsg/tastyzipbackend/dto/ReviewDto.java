package com.sdsgsg.tastyzipbackend.dto;

import com.sdsgsg.tastyzipbackend.domain.Review;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDto {
	private String content;
	private Double rating;

	@Builder
	public ReviewDto(String content, Double rating) {
		this.content = content;
		this.rating = rating;
	}

	public static ReviewDto fromEntity(Review review) {
		return builder()
			.content(review.getContent())
			.rating(0.0)
			.build();
	}
}
