package com.sdsgsg.tastyzipbackend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdsgsg.tastyzipbackend.domain.Restaurant;
import com.sdsgsg.tastyzipbackend.domain.Review;
import com.sdsgsg.tastyzipbackend.dto.RestaurantDetailsDto;
import com.sdsgsg.tastyzipbackend.repository.RestaurantRepository;
import com.sdsgsg.tastyzipbackend.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import scala.collection.Seq;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;
	private final ReviewRepository reviewRepository;

	public List<RestaurantDetailsDto> searchRestaurants(String searchKeyword) {
		List<String> keywords = keywordExtraction(searchKeyword);

		HashMap<Restaurant, Long> searchMap = countKeyword(keywords);

		List<Restaurant> keySet = new ArrayList<>(searchMap.keySet());
		keySet.sort((o1, o2) -> {
			if (searchMap.get(o2).compareTo(searchMap.get(o1)) == 0) {
				return o2.getScore().compareTo(o1.getScore());
			}
			return searchMap.get(o2).compareTo(searchMap.get(o1));
		});

		List<RestaurantDetailsDto> results = keySet.stream()
			.map(RestaurantDetailsDto::fromEntity)
			.collect(Collectors.toList());
		if (results.size() > 50) {
			results = results.subList(0, 50);
		}
		return results;
	}

	private HashMap<Restaurant, Long> countKeyword(List<String> keywords) {
		HashMap<Restaurant, Long> searchMap = new HashMap<>();

		for (String keyword : keywords) {
			List<Review> byKeyword = reviewRepository.findReviewsByKeyword(keyword);

			for (Review review : byKeyword) {
				Restaurant restaurant = review.getRestaurant();
				if (searchMap.containsKey(restaurant)) {
					searchMap.put(restaurant, searchMap.get(restaurant) + 1);
				} else {
					searchMap.put(restaurant, 1L);
				}
			}
		}

		return searchMap;
	}

	private List<String> keywordExtraction(String searchKeyword) {
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(searchKeyword);
		Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
		List<KoreanTokenJava> tokenList = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);
		List<String> alist = List.of("Noun", "Modifier", "Adjective", "Foreign");

		List<String> keywords = new ArrayList<>();
		for (KoreanTokenJava token : tokenList) {
			if (alist.contains(token.getPos().toString())) {
				keywords.add(token.getText());
			}
		}

		return keywords;
	}

	public RestaurantDetailsDto getRestaurantDetailsDto(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("id에 해당하는 음식점이 없습니다"));

		return RestaurantDetailsDto.fromEntity(restaurant);
	}
}
