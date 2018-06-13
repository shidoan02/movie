package com.mooovi.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mooovi.business.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
