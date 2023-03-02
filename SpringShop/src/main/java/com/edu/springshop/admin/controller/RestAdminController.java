package com.edu.springshop.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springshop.domain.Admin;
import com.edu.springshop.exception.AdminException;
import com.edu.springshop.model.admin.AdminService;
import com.edu.springshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestAdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login/admin")
	public ResponseEntity<Message> login(Admin admin, HttpServletRequest request) {
		System.out.println(admin);
		
		//3단계
		Admin result = adminService.login(admin);
		
		//4단계?
		HttpSession session = request.getSession();
		session.setAttribute("admin", result);
		
		Message message = new Message();
		message.setMsg("어드민 로그인 성공");
		message.setCode(200);
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
//	@ExceptionHandler(AdminException.class)
//	public ResponseEntity<Message> handle(RuntimeException e){
//		Message message = new Message();
//		message.setMsg(e.getMessage());
//		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
//		return entity;
//	}
}
