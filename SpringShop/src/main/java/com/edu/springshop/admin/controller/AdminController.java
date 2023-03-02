package com.edu.springshop.admin.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.domain.Admin;
import com.edu.springshop.exception.AdminException;
import com.edu.springshop.model.admin.AdminService;

@Controller
public class AdminController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/main")
	public ModelAndView getMain(HttpServletRequest request) throws AdminException{
		//logger.info("123123");
		
		//로그인 인증 여부를 따져봐야 한다
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		
		ModelAndView mav = new ModelAndView("/admin/index");
		
		if(admin==null) throw new AdminException("로그인이 필요한 서비스입니다");
		return mav;
	}
	
	@GetMapping("/loginform")
	public ModelAndView getLoginform(HttpServletRequest request) {
		return new ModelAndView("/admin/login/loginform");
	}
	
	//로그인 요청 처리
	@PostMapping("/login")
	public ModelAndView login(HttpServletRequest request, Admin admin) { 
		//3단계
		Admin result = adminService.login(admin);
		
		
		ModelAndView mav = new ModelAndView("redirect:/admin/main");
		return mav;
	}
	
//	@ExceptionHandler(AdminException.class)
//	public ModelAndView handle(RuntimeException e) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("e", e);
//		mav.setViewName("/admin/error/result");
//		return mav;
//	}
	
}
