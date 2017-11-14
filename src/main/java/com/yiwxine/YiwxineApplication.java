package com.yiwxine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yiwxine.util.HttpRequest;

@SpringBootApplication
public class YiwxineApplication {

	public static void main(String[] args) {
		SpringApplication.run(YiwxineApplication.class, args);
		//String sr = HttpRequest.sendPost("http://10.1.65.33:81/login", "account=testtest&password=123456");
		//System.out.println(sr);
	}
}
