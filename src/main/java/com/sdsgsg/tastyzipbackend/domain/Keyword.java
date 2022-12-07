package com.sdsgsg.tastyzipbackend.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class Keyword {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "keyword_id")
	private Long id;

	private String keyword;

	@OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL)
	private List<Connect> connect;
}
