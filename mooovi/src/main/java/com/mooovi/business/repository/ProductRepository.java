package com.mooovi.business.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mooovi.business.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByTitle(String title);

	// DB検索
	List<Product> findAllByTitleLike(String keyword);

	// JPSQL発行
	@Query("SELECT r.product FROM Review r GROUP BY r.product ORDER BY COUNT(r.product) DESC")
	Page<Product> findTop(Pageable pageable);

}
/* ProductServiceメソッド */