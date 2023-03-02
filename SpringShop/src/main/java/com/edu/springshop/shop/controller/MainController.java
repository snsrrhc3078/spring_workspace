package com.edu.springshop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.model.category.CategoryService;

@Controller
public class MainController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public ModelAndView getMain(HttpServletRequest request) {
		
		//3단계
		List categoryList = categoryService.selectAll();
		
		//4단계 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/shop/index");
		mav.addObject("categoryList", categoryList);
		
		return mav;
	}
}
