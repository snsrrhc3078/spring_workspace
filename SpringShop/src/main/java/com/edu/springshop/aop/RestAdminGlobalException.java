package com.edu.springshop.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edu.springshop.exception.AdminException;
import com.edu.springshop.util.Message;

/*
 	관리자와 관련된 글러볼 예외처리 객체
 	단, 응답 형식은 jsp가 아닌 json과 같은 데이터 형태가 되어야 하므로,
 	ResponseEntity로 응답한다
 */
//RestControllerAdvice = ControllerAdvice + ResponseBody 이므로 자동으로 RestController를 찾지는 않는다
//여기서 annotations 를 붙여서  RestController 만 잡으라고 명시해야만 RestController만 잡을 수 있다
@RestControllerAdvice(annotations = RestController.class)
public class RestAdminGlobalException {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@ExceptionHandler(value = AdminException.class)
	public ResponseEntity<Message> handle(RuntimeException e){
		logger.info("관리자의 Rest 글로벌 예외에서 감지한 회원등록 실패");
		
		Message message = new Message();
		message.setMsg("Admin Rest 글로벌 예외 : " + e.getMessage());
		
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
