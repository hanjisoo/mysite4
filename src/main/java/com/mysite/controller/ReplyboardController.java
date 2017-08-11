package com.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.replyboardService;
import com.mysite.vo.ReplyboardVo;
import com.mysite.vo.UserVo;



@Controller
public class ReplyboardController {
	
	@Autowired
	private replyboardService replyService;
	
	@RequestMapping("/reply/list")
	public String list (Model model) {
		List<ReplyboardVo> list=replyService.list();
		model.addAttribute("list", list);
		return "reply/list";
	}
	
	@RequestMapping("/reply/writeform")
	public String writeform() {
		return "reply/writeform";
	}
	
	@RequestMapping("/reply/write")
	public String write(@ModelAttribute ReplyboardVo replyVo, HttpSession session) {//userNo까지 보내줘야함
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		replyVo.setUserNo(no);
		
		System.out.println(authUser.toString());
		System.out.println(replyVo.toString());
		replyService.write(replyVo);
		return "redirect:/reply/list";
	}
	
	@RequestMapping("/reply/read/{no}")
	public String read(Model model,@PathVariable("no") int no) {
		ReplyboardVo replyVo=replyService.getRead(no);
		model.addAttribute("replyVo", replyVo);
		System.out.println("브이오"+replyVo.toString());
		return "reply/read";
	}
	
	@RequestMapping("/reply/replyform")
	public String replyform(@RequestParam int no, Model model) {
		ReplyboardVo replyVo=replyService.getRead(no);
		model.addAttribute("replyVo",replyVo);
		System.out.println("댓글창"+replyVo);
		return "reply/replyform";
	}
	
	@RequestMapping("/reply/reply")
	public String reply(@ModelAttribute ReplyboardVo replyVo, HttpSession session) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		replyVo.setUserNo(no);
		
		System.out.println(authUser.toString());
		System.out.println(replyVo.toString());
		replyService.reply(replyVo);
		return "redirect:/reply/list";
	}
	
}
