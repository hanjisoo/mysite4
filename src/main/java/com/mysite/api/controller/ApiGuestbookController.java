package com.mysite.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.service.GuestbookService;
import com.mysite.vo.GuestbookVo;

@Controller
@RequestMapping(value="/api/book")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="list",method=RequestMethod.POST)
	public List<GuestbookVo> list() {
		
		List<GuestbookVo> list=guestbookService.list();
		return list;//주소로 보내는게 아니고 데이터만 보내 responseBody라고 써서 바디에실어서 보내.
		
		
		/*String str="ok";
		return str;//문자열로 봐라*/
		
	}
	
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	//3개받으니깐 vo로 담아서 저장								//주소를 보내주고 있는거임
	public GuestbookVo add2(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println(guestbookVo.toString());
		GuestbookVo guestbookVo2=guestbookService.add2(guestbookVo);
		System.out.println(guestbookVo);
		return guestbookVo2;
	}
	
	
	
	
	
}
