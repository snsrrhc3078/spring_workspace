package com.edu.springboard.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticeController {
	@GetMapping("/notice/list")
	public ModelAndView noticeMain() {
		return new ModelAndView("notice/list");
	}
	
	@GetMapping("/notice/registform")
	public ModelAndView registForm() {
		return new ModelAndView("/notice/regist");
	}
	
	@GetMapping("/notice/detail")
	public ModelAndView detail(int notice_idx) {
		
		
		//여기서 3단계 업무 시켜도 된다, 4단계도 가능
		//하지만 수업 주제가 RESTful 이므로 js에서 상세요청을 처리하겠음
		ModelAndView mav = new ModelAndView("/notice/detail");
		mav.addObject("notice_idx", notice_idx);
		return mav;
	}
}
