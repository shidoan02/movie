package com.mooovi.business.service;

import org.springframework.data.domain.Page;

import com.mooovi.business.entity.Product;
import com.mooovi.business.entity.Review;

public interface ReviewService {

	//DBに保存
	//void save (Review review);
	void save(Review review, Long productId);

	Page<Product> findTop5();
	
	//アソシエーション保存
	void save(Review review, Long productId, Long userId);

}
