package com.edu.springshop.admin.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springshop.domain.Product;
import com.edu.springshop.exception.PimgException;
import com.edu.springshop.exception.ProductException;
import com.edu.springshop.exception.UploadException;
import com.edu.springshop.model.product.ProductService;
import com.edu.springshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestProductController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//서비스의 존재가 없다면, 컨트롤러 계층과 모델 계층의 구분이 모호해진다..
	//추후 모델을 완전히 재사용을 위해 분리시키려 할 떄 분리가 불가능하다..
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public Message regist(Product product, HttpServletRequest request) {
		
		ServletContext context = request.getSession().getServletContext();
		logger.info("realpath in context in session : " + context.getRealPath("/resources/data"));
		
		//3단계
		productService.regist(product, context.getRealPath("/resources/data/"));
		
		Message message = new Message();
		
		return null; 
	}
	
	@ExceptionHandler(UploadException.class)
	public ResponseEntity<Message> handler(UploadException e){
		
		Message message = new Message();
		message.setMsg(e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<Message> handler(ProductException e){
		
		Message message = new Message();
		message.setMsg(e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	@ExceptionHandler(PimgException.class)
	public ResponseEntity<Message> handler(PimgException e){
		
		Message message = new Message();
		message.setMsg(e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	
}
