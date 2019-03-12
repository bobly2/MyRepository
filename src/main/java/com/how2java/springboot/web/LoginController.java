package com.how2java.springboot.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import com.how2java.springboot.pojo.User;
import com.how2java.springboot.service.UserService;

@Controller
public class LoginController {
	@Autowired UserService userService;
	@RequestMapping("/")
	
	public String login(Model m) throws Exception{
		m.addAttribute("name", "thymeleaf");
		return "login";
	}
	@RequestMapping("/dologin")
	public String loginon(@Param(value = "username") String username,
			@Param(value = "password") String password)throws Exception{
		
		User user= userService.login(username, password);
		String str = "";
		if (user != null) {
			str = "success";
		} else {
			str = "errorPage";
		}
		return str;
	}
	@RequestMapping("/register")
	public String gister() throws Exception{
		return "register";
	}
	@RequestMapping("/doregister")
	public String gisterOn(@Param(value = "username") String username,
			@Param(value = "email") String email,
			@Param(value = "password") String password,
			@Param(value = "password2") String password2) throws Exception{
		String str1=new String();
		int i = userService.checkEmailExist(email);
		int j = userService.checkUsernameExist(username);
		if (i==0 || j==0) {
			str1 = "errorPage3";
			return str1;
		}else {
			userService.gister(username,email,password);
			str1 = "success2";
			return str1;
		}
		
	}

}
