package com.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping(value="/main") //주소
	public String main() {
		return "main/index";	//spring-servlet에 앞뒤주소 세팅해놈
	}
}
