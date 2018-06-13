package com.mooovi.web.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mooovi.business.entity.Product;
import com.mooovi.business.service.ProductService;
import com.mooovi.security.LoginUserDetails;


//クラス内でのコントローラー共通処理は@ControllerAdvice
@ControllerAdvice
public class CommonControllerAdvice {
	
	@Autowired
    private ProductService productService;

    @ModelAttribute(name = "rankingProducts")
    private Page<Product> setupRankingProducts() {
        return productService.findTop5();
    }
    
    //@AuthenticationPrincipalより,
    //ログインしているユーザの情報をLoginUserDetailsクラスとして引数で受け取る
    @ModelAttribute(name = "loginUser")
    private LoginUserDetails setupLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        return loginUserDetails;
    }
    
}
