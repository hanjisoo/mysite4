package com.mysite.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysite.service.BoardService;
import com.mysite.vo.BoardVo;
import com.mysite.vo.UserVo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/list")
	public String list(Model model){
		List<BoardVo> list1=boardService.list();
		/*System.out.println(list1.toString());*/
		model.addAttribute("list", list1);
		return "board/list";
	}
	
	@RequestMapping(value="/board/read/{no}")
	public String read(Model model, @PathVariable("no") int no) {
		
		BoardVo boardVo =boardService.getRead(no);
		/*System.out.println(boardVo.toString());*/
		model.addAttribute("boardVo", boardVo);
		return "board/read";
	}
	
	@RequestMapping("/board/writeform")
	public String writeform() {
		return "board/writeform";
	}
	
	@RequestMapping("/board/write")
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {//userNo까지 보내줘야함
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		boardVo.setUserNo(no);//boardVo에 no넣음
		
		System.out.println(authUser.toString());
		System.out.println(boardVo.toString());
		boardService.write(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.GET)
	public String delete(HttpSession session, @ModelAttribute BoardVo vo) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		vo.setUserNo(no);
		
		boardService.delete(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/modifyform")
	public String modifyform(@ModelAttribute BoardVo no, Model model) {

		boardService.getUser(no);
	
		model.addAttribute("boardNo", no);
		
		System.out.println("보드"+no);
		return"board/modifyform";
	}

	
	
	
	
	
	
	@RequestMapping("/board/modify")
	public String modify(@ModelAttribute BoardVo boardVo,HttpSession session){
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		boardVo.setNo(no);
		
		boardService.update(boardVo);
		return "redirect:/board/list";
	}
	
	
	
	
	
	
}
