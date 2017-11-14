package com.yiwxine.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiwxine.dao.UserDao;
import com.yiwxine.domain.User;
import com.yiwxine.service.UserService;
import com.yiwxine.util.UserFrom;

@Controller
public class LoginController {

	@Resource
	private UserService userService;
	
	@Resource
	private UserDao userDao;
	
	@GetMapping("/login")
    public String login(){
			return "login";
		}
		
	
	@PostMapping("/login")
	@ResponseBody
	public String login(HttpSession httpSession,@Valid UserFrom userFrom){
		User user = userDao.findByAccount(userFrom.getAccount());
		if(null != user){
			if(StringUtils.equals(user.getPasswd(), userFrom.getPasswd())){
			   httpSession.setAttribute("seuser", user);
				return "success";
			}
			return "passwd_error";
		}else{
			return "account_not_exit";
		}
		
	}
	
}
