package com.mooovi.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mooovi.business.entity.Product;

public interface ProductService {

	//Scraping実行
	Product save(Product product);

	Product findOneOrNew(String title);

    Page<Product> findAll(Pageable pageable);
    
    //topページから画像クリックし、show.htmlへ遷移
    Product findOne(Long id);

    //DB検索
	List<Product> findAllByTitleLike(String keyword);
	
	//JPSQL発行
	Page<Product> findTop5();
    
}
