package com.edu.springshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.domain.Category;
import com.edu.springshop.domain.Product;
import com.edu.springshop.exception.CategoryException;
import com.edu.springshop.model.category.CategoryService;
import com.edu.springshop.model.product.ProductService;
import com.edu.springshop.util.Message;

@Controller
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/registform")
	public ModelAndView getForm() {
		
		//3단계
		List<Category> categoryList = categoryService.selectAll();
		ModelAndView mav = new ModelAndView("/admin/product/registform");
		mav.addObject("categoryList", categoryList);
		
		return mav;
	}
	
	@PostMapping("/product")
	public ResponseEntity<Message> regist(Product product){
		
		
		
		Message message = new Message();
		message.setMsg("PUT성공");
		message.setCode(201);
		
 		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.CREATED);
		return entity;
	}
	
	@GetMapping("/product/list")
	public ModelAndView getList() {
		
		//3단계
		List productList = productService.selectAll();
		
		//4단계
		ModelAndView mav = new ModelAndView("/admin/product/list");
		mav.addObject("productList", productList);
		
		return mav;
	}
	
	@GetMapping("/product/detail")
	public ModelAndView getDetail(Product product) {
		//3단계
		product = productService.select(product.getProduct_idx());
		List<Category> categoryList = categoryService.selectAll();
		
		
		//4단계
		ModelAndView mav = new ModelAndView("/admin/product/detail");
		mav.addObject("product", product);
		mav.addObject("categoryList", categoryList);
		return mav;
	}
	
	@ExceptionHandler(CategoryException.class)
	public ModelAndView handler(CategoryException e) {
		return null;
	}
}
