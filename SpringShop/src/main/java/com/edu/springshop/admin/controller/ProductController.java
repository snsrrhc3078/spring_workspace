package com.edu.springshop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.domain.Category;
import com.edu.springshop.exception.CategoryException;
import com.edu.springshop.model.category.CategoryService;

@Controller
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/product/registform")
	public ModelAndView getForm() {
		
		//3단계
		List<Category> categoryList = categoryService.selectAll();
		ModelAndView mav = new ModelAndView("/admin/product/registform");
		mav.addObject("categoryList", categoryList);
		
		return mav;
	}
	
	@ExceptionHandler(CategoryException.class)
	public ModelAndView handler(CategoryException e) {
		return null;
	}
}
