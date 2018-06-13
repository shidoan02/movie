package com.mooovi.web.controller;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mooovi.business.entity.User;
import com.mooovi.business.service.UserService;
import com.mooovi.security.LoginUserDetails;
import com.mooovi.web.form.UserForm;

@Controller
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginForm(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		if (loginUserDetails != null) {
			return "redirect:/";
		}
		return "users/loginForm";
	}

	//マイページの表示
	@GetMapping("/users/{id}")
	public String show(@PathVariable Long id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "users/show";
	}

	// サインアップ画面
	@GetMapping("/sign_up")
	public String signupForm(UserForm form, @AuthenticationPrincipal LoginUserDetails loginUserDtails) {
		if (loginUserDtails != null) {
			return "redirect:/";
		}
		return "users/signupForm";
	}

	@PostMapping("/sign_up")
	public String register(@Validated UserForm form, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUserDetails loginUserDetails) throws IOException{
			//@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		if (loginUserDetails != null) {
			return "redirect:/";
		}
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			result.rejectValue("password", "error.passwordConfirmation", "do not match.");
		}
		if (result.hasErrors()) {
			return "users/signupForm";
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userService.save(user, form.getFile());
		//userService.save(user);
		return "redirect:/";

	}
	
	//ユーザーidの画像をダウンロード
	@GetMapping("/users/{id}/profile-image.jpg")
	@ResponseBody //httpのレスポンスとしてオブジェクトを返す
	public byte[] downloadProfileImage(@PathVariable Long id) throws IOException {
	    return userService.downloadProfileImage(id);
	}
}
