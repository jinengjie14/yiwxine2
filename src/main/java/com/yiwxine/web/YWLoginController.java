package com.yiwxine.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.yiwxine.service.UserService;
import com.yiwxine.util.UserFrom;

@Controller
public class YWLoginController {

	@Resource
	private UserService userService;
	
	@GetMapping("/ywlogin")
    public String login(){
			return "ywlogin";
		}
		
	
	@PostMapping("/ywlogin")
	public String ywlogin(HttpSession session,@Valid UserFrom userFrom){
		userService.ywlogin(session,userFrom);
		return "index";
	}
	
}
