package com.yiwxine.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiwxine.dao.UserDao;
import com.yiwxine.domain.User;
import com.yiwxine.util.HttpRequest;
import com.yiwxine.util.ServiceException;
import com.yiwxine.util.UserFrom;


@Service
@Transactional
public class UserService {
     @Resource
     private UserDao userDao;
	
	public void Register(UserFrom userFrom){
		
		User user = new User();
		user.init();
		user.setAccount(userFrom.getAccount());
		user.setPasswd(userFrom.getPasswd());
		userDao.save(user);
	}
	
	public User findById(String id,String seuserid){
		
		try {
			if(!StringUtils.isBlank(seuserid))
				throw new ServiceException("login","你还没有登录");
			User user = userDao.findById(id);
			return  user;
		} catch (ServiceException re) {
			
			throw  re;
		}
		
	}
	
	public void UserMerge(UserFrom userFrom,String id){
		
		try {
			User user = userDao.findById(id);
			BeanUtils.copyProperties(userFrom, user, User.class);
			userDao.merge(user);
		} catch (ServiceException re) {
			throw re;
		}
	}
	
	
	public void ywlogin(HttpSession session,UserFrom userFrom){
		
		try {
			String sr = HttpRequest.sendPost("http://10.1.65.33:81/login","account="+userFrom.getAccount()+"&"+"password="+userFrom.getPasswd());
			JSONObject jo = JSON.parseObject(sr);         //将访问获取到的数据转化成一个JSON对象
			System.err.println(sr);
			String data = jo.get("data").toString();        //将JSON data属性转换成一个字符串
			User user = JSON.parseObject(data, User.class);    //将转换后的字符串存进user对象里面
			session.setAttribute("ywseuser", user);
		} catch (ServiceException re) {
		   throw re;
		}
		
	}
	
	
}
