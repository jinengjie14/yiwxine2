package com.yiwxine.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yiwxine.service.UserService;
import com.yiwxine.util.JsonResult;
import com.yiwxine.util.UserFrom;

@Controller
public class RegisterController {

	@Resource
	private UserService userService;
	
	@GetMapping("/register")
	public String register(){
		return "register";
	}
	

	@PostMapping("/register")
	@ResponseBody
	public String Register(@Valid UserFrom userfrom,BindingResult result,Model model){
		List<JsonResult> arr = new ArrayList<JsonResult>();
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				FieldError fe = (FieldError)error;
				JsonResult jr = new JsonResult();
				jr.setField(fe.getField());
				jr.setMessage(error.getDefaultMessage());
				arr.add(jr);
			}
			return JSON.toJSONString(arr);
		}
		else{
		userService.Register(userfrom);
		return "success";
		}
		
		
	
	}
	}
	

