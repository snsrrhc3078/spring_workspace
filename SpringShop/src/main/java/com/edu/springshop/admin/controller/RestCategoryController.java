package com.edu.springshop.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springshop.domain.Category;
import com.edu.springshop.exception.CategoryException;
import com.edu.springshop.model.category.CategoryService;
import com.edu.springshop.util.Message;

@RestController
@RequestMapping("/rest")
public class RestCategoryController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategoryService categoryService;
	
	//등록 요청
	@PostMapping("/category")
	public ResponseEntity<Message> regist(Category category) {
		
		//3단계
		categoryService.insert(category);
		
		Message message = new Message();
		message.setMsg("POST성공");
		message.setCode(201);
		
 		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	//목록 요청
	@GetMapping("/category")
	public List<Category> getList(){
		
		//3단계
		List<Category> categoryList = categoryService.selectAll();
		
		return categoryList;
	}
	
	//수정 요청
	//@ResponseBody : 자바객체 -> JSON
	//@RequestBody : JSON -> 자바객체
	@PutMapping("/category")
	public ResponseEntity<Message> update(@RequestBody Category category) {
		logger.trace("category is : " + category);
		logger.debug("category is : " + category);
		logger.info("category is : " + category);
		logger.warn("category is : " + category);
		logger.error("category is : " + category);
		
		categoryService.update(category);
		
		
		Message message = new Message();
		message.setMsg("PUT성공");
		message.setCode(201);
		
 		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	//삭제 요청
	//파라미터가 평소(?변수명=값)와는 다르게 디렉토리의 일부로 전달된다
	//따라서 스프링측에서 경로에 포함된 파라미터를 변수로 인실할 필요가 있다
	//이 문제를 가능하게 해주는 코드 경로에 변수 부분을 {변수}로 표현
	//@PathVariable 로 사용
	@DeleteMapping("/category/{category_idx}")
	public ResponseEntity<Message> delete(@PathVariable int category_idx){
		
		categoryService.delete(category_idx);
		
		logger.info(category_idx + "");
		
		Message message = new Message();
		message.setMsg("DELETE성공");
		message.setCode(200);
		
 		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
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
