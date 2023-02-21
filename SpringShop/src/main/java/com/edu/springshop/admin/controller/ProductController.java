package com.edu.springshop.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
	@GetMapping("/product/registform")
	public ModelAndView getForm() {
		return new ModelAndView("/admin/product/registform");
	}
}
