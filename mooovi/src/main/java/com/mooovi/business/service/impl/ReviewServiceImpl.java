package com.mooovi.business.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mooovi.business.entity.Product;
import com.mooovi.business.entity.Review;
import com.mooovi.business.repository.ProductRepository;
import com.mooovi.business.repository.ReviewRepository;
import com.mooovi.business.service.ProductService;
import com.mooovi.business.service.ReviewService;
import com.mooovi.business.service.UserService;

/* ReviewServiceインターフェースの実装クラス*/

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired // reviewRepositoryをインジェクション 宣言必須！！
	private ReviewRepository reviewRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

    @Autowired
    private UserService userService;
	
	@Override
	public void save(Review review, Long productId) {

		review.setProduct(productService.findOne(productId));
		reviewRepository.save(review);
	}

	@Override
	public Page<Product> findTop5() {
		// pageRequestインスタンス作成(ページ番号，１ページの表示件数)
		return productRepository.findTop(new PageRequest(0, 5));
	}

	@Override
	public void save(Review review, Long productId, Long userId) {
		review.setProduct(productService.findOne(productId));
		review.setUser(userService.findOne(userId));
		reviewRepository.save(review);
	}

}
