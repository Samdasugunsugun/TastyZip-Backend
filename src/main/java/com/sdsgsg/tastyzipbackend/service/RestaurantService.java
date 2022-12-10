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

import com.sdsgsg.tastyzipbackend.domain.Keyword;
import com.sdsgsg.tastyzipbackend.domain.Restaurant;
import com.sdsgsg.tastyzipbackend.domain.Review;
import com.sdsgsg.tastyzipbackend.dto.RestaurantDetailsDto;
import com.sdsgsg.tastyzipbackend.repository.KeywordRepository;
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
	private final KeywordRepository keywordRepository;

	public List<RestaurantDetailsDto> searchRestaurants(String searchKeyword) {
		List<String> keywords = keywordExtraction(searchKeyword);

		List<Restaurant> restaurants = restaurantRepository.findAllRestaurantReviews();
		HashMap<Restaurant, Long> searchMap = countKeyword(restaurants, keywords);

		List<Restaurant> keySet = new ArrayList<>(searchMap.keySet());
		keySet.sort((o1, o2) -> searchMap.get(o2).compareTo(searchMap.get(o1)));

		return keySet.stream()
			.map(RestaurantDetailsDto::fromEntity)
			.collect(Collectors.toList());
	}

	private HashMap<Restaurant, Long> countKeyword(List<Restaurant> restaurantList, List<String> keywords) {
		HashMap<Restaurant, Long> searchMap = new HashMap<>();

		for (Restaurant restaurant : restaurantList) {
			long count = 0L;
			List<Review> reviews = reviewRepository.findByRestaurant(restaurant);
			for (Review review : reviews) {
				List<String> reviewKeywords = keywordRepository.findByReview(review).stream()
					.map(Keyword::getKeyword)
					.collect(Collectors.toList());
				reviewKeywords.retainAll(keywords);
				count += reviewKeywords.size();
			}

			if (count > 0) {
				searchMap.put(restaurant, count);
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
