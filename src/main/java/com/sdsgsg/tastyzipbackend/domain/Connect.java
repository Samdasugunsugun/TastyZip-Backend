package com.sdsgsg.tastyzipbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class Connect {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "connect_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Review review;

	@ManyToOne(fetch = FetchType.LAZY)
	private Keyword keyword;
}
