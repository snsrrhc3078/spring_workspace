package com.edu.mvc2.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegistFormController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//게시판 등록 폼의 이름 등록
		ModelAndView mav = new ModelAndView("/board/regist");
		
		return mav;
	}
	
}
