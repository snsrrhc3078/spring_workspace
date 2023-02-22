package com.edu.springshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springshop.domain.Category;
import com.edu.springshop.exception.CategoryException;
import com.edu.springshop.model.category.CategoryService;
import com.edu.springshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	public Message regist(Category category) {
		
		//3단계
		categoryService.insert(category);
		
		Message message = new Message();
		message.setMsg("성공");
		message.setCode(200);
		return message;
	}
	
	@GetMapping("/category")
	public List<Category> getList(){
		
		//3단계
		List<Category> categoryList = categoryService.selectAll();
		
		return categoryList;
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<Message> handler(CategoryException e) {
		
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		//HTTP 응답정보를 보다 세밀하게 구성하고 싶다면..
		//http응답 메시지를 구성할 수 있는 객체를 지원함
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
}
