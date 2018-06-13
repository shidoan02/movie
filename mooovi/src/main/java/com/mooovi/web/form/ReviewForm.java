package com.mooovi.web.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReviewForm {

//	@NotBlank
//	private String nickname;

	@NotNull
	@Min(1)
	@Max(10)
	private Integer rate;

	@NotBlank
	private String comment;
	
	//
//	public String getNickname() {
//		return nickname;
//	}
//
//	public void setNickname(String nickname) {
//		this.nickname=nickname;
//	}
	
	public Integer getRate() {
		return rate;
	}
	
	public void setRate(Integer rate) {
		this.rate=rate;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void setComment(String comment) {
		this.comment=comment;
	}
	
}
