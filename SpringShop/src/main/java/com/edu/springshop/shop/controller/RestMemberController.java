package com.edu.springshop.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springshop.domain.Member;
import com.edu.springshop.exception.HashException;
import com.edu.springshop.exception.MemberException;
import com.edu.springshop.model.member.MemberService;
import com.edu.springshop.util.Message;


@RestController
@RequestMapping("/rest")
public class RestMemberController {
	
	@Autowired
	private MemberService memberService;
	
	//회원가입 요청 처리
	@PostMapping("/member")
	public ResponseEntity<Message> regist(Member member) {
		
		//3단계
		memberService.regist(member);
		
		Message message = new Message();
		message.setCode(201);
		message.setMsg("회원등록 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@ExceptionHandler(HashException.class)
	public ResponseEntity<Message> handle(HashException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@ExceptionHandler(MailException.class)
	public ResponseEntity<Message> handle(MailException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Message> handle(MemberException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
}
