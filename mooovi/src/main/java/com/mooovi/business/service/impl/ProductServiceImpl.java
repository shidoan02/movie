package com.mooovi.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mooovi.business.entity.Product;
import com.mooovi.business.repository.ProductRepository;
import com.mooovi.business.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Scraping実行
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	// 同じタイトルの映画はDBに保存しない
	@Override
	public Product findOneOrNew(String title) {
		Product product = productRepository.findByTitle(title);
		if (product == null)
			product = new Product();
		return product;
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	// topページから画像クリックし、show.htmlへ遷移
	@Override
	public Product findOne(Long id) {
		return productRepository.findOne(id);
	}

	// DB検索
	@Override
	public List<Product> findAllByTitleLike(String keyword) {
		return productRepository.findAllByTitleLike("%" + keyword + "%");
	}

	@Override
	public Page<Product> findTop5() {
		// pageRequestインスタンス作成(ページ番号，１ページの表示件数)
		return productRepository.findTop(new PageRequest(0, 5));

	}

}
