package com.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.UserService;
import com.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")//이거 쓰면 중복안하고 user빼줄수 있음
public class UserController {

	
	@Autowired	//서비스랑 연결
	private UserService userService;
	
	
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)//주소갖고 있음 웹에서 쓰겠다.
	public String joinform() {
		return "user/joinform";//spring-servlet에서 관리
	}//화면띄움
	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(userVo.toString());
		userService.join(userVo);//서비스로 보내줄껴!
		return"user/joinsuccess";
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email")String email,
						@RequestParam("password")String password,
						HttpSession session) {//<-세션에 돌아온거 널어줘야되니깐 만들어줌
		
		UserVo authUser=userService.getUser(email,password);//이런사람있으면 가져와
			  //돌아올떄 여기에 담길꺼랃구
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		}else {
			return "redirect:/user/loginform?result=fail";
		}
		/*System.out.println(authUser.toString());//로그인누를*/		
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		
		//돌아올떄
		UserVo userVo=userService.getUser(no);
		model.addAttribute("userVo",userVo);
		return "user/modifyform";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute UserVo userVo,HttpSession session) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		userVo.setNo(no);
		
		userService.updateUser(userVo);
		
		return "redirect:/main";
	}
}
