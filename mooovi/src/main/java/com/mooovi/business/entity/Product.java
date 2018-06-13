package com.mooovi.business.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String imageUrl;

	private String director;

	@Column(columnDefinition = "TEXT")
	private String detail;

	private String openDate;

	// ゲッターセッター
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	// 1対多
	@OneToMany(mappedBy = "product")
	private List<Review> reviews;

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	// 星の平均値を計算
	public int AverageRate() {
		double sum = 0;
		
		// reviews →テーブル名 review →カラム名
		for (Review review : reviews) {
			sum += review.getRate();
		}
		double average;
		//review.size=0が trueの時，average=0
		//falseの時　sum / reviews.size()
		average = reviews.size() == 0 ? 0 : sum / reviews.size();
		return (int) Math.round(average);
	}
}
