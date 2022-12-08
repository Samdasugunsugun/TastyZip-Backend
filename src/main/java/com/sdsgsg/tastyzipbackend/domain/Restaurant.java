package com.sdsgsg.tastyzipbackend.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private Long id;

	private String name;

	private String category;

	private String address;

	private Double lat;
	private Double lon;

	private Double score;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<Review> reviews;
}
