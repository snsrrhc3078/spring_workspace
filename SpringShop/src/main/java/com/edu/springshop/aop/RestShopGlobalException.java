package com.edu.springshop.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edu.springshop.exception.EmailException;
import com.edu.springshop.exception.HashException;
import com.edu.springshop.exception.MemberException;
import com.edu.springshop.exception.UploadException;
import com.edu.springshop.util.Message;

//하위 컨트롤러에서 해당 예외가 발생했을 때, 해당 하위 컨트롤러
//에서 지정된 @ExceptionHandler 가 먼저 적용되고,
//만일 해당 @ExceptionHandler 가 지정되지 않으면, 이 예외
//객체가 예외를 처리할 것임
//@RestControllerAdvice 자체가 RestController에서 발생하는 예외만을 처리한다는
//보장을 하지 않는다.
@RestControllerAdvice(annotations = RestController.class) // @ControllerAdvice + @ResponseBody
public class RestShopGlobalException {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = MemberException.class)
	public ResponseEntity<Message> handle(RuntimeException e){
		logger.info("글로벌 예외에서 Member 회원 등록 실패");
		Message message = new Message();
		message.setMsg("쇼핑몰 글로벌 예외 : " + e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	@ExceptionHandler(value = HashException.class)
	public ResponseEntity<Message> handle2(RuntimeException e){
		logger.info("글로벌 예외에서 Hash 회원 등록 실패");
		Message message = new Message();
		message.setMsg("쇼핑몰 글로벌 예외 : " + e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	@ExceptionHandler(value = EmailException.class)
	public ResponseEntity<Message> handle3(RuntimeException e){
		logger.info("글로벌 예외에서 Email 회원 등록 실패");
		Message message = new Message();
		message.setMsg("쇼핑몰 글로벌 예외 : " + e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}
