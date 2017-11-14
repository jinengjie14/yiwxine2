package com.yiwxine.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


public class UserFrom {
    
	@NotNull(message="账号不能为空")
	@Size(min=5,message="请输入至少5位数字，字母")
	private String account;
	@NotNull(message="密码不能为空")
	@Size(min=6,message="请输入至少6位数字，字母")
	private String passwd;
	


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
