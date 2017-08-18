package com.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysite.service.GuestbookService;
import com.mysite.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/book/list-ajax")
	public String listAjax() {
		return "book/list-ajax";
	}
	@RequestMapping("/book/list")
	public String list(Model model) {//데이터받으려면 모델필요!!!
		List<GuestbookVo> list1= guestbookService.list();
		System.out.println(list1.toString());
		model.addAttribute("list",list1);
		return "book/list";	
	}
	
	
	
	@RequestMapping(value="/book/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo){
		System.out.println(vo.toString());
		guestbookService.add(vo);
		return"redirect:/book/list";
	}
	
	
	@RequestMapping(value="/book/delete", method=RequestMethod.POST)//jsp에서 post로 받고 있으니깐 적어줘야함
	public String delete(@ModelAttribute GuestbookVo vo) {
		System.out.println("삭제"+vo.toString());
		guestbookService.delete(vo);
		return"redirect:/book/list";
	}
	
	@RequestMapping(value="/book/deleteform/{no}")
	public String deleteform(Model model, @PathVariable("no") int no) {
		System.out.println("이히");
		model.addAttribute("num", no);
		return "book/deleteform";
	}
}
