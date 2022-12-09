package com.sdsgsg.tastyzipbackend.dto;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantDetailsDto {
	private Long id;
	private String name;
	private String category;
	private String address;
	private Double rating;
	private Double lat;
	private Double lon;

	@Builder
	public RestaurantDetailsDto(Long id, String name, String category, String address, Double rating, Double lat,
		Double lon) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.address = address;
		this.rating = rating;
		this.lat = lat;
		this.lon = lon;
	}

	public static RestaurantDetailsDto fromEntity(Restaurant restaurant) {
		return builder()
			.id(restaurant.getId())
			.name(restaurant.getName())
			.category(restaurant.getCategory())
			.address(restaurant.getAddress())
			.lat(restaurant.getLat())
			.lon(restaurant.getLon())
			.rating(restaurant.getScore())
			.build();
	}
}
