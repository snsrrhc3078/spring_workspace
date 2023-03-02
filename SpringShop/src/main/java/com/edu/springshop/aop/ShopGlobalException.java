package com.edu.springshop.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springshop.exception.EmailException;
import com.edu.springshop.exception.HashException;
import com.edu.springshop.exception.MemberException;

@ControllerAdvice(annotations = Controller.class)
public class ShopGlobalException {
	@ExceptionHandler(MemberException.class)
	public ModelAndView handle(RuntimeException e) {
		e.printStackTrace();
		ModelAndView mav = new ModelAndView("/shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
	@ExceptionHandler(HashException.class)
	public ModelAndView handle2(RuntimeException e) {
		ModelAndView mav = new ModelAndView("/shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
	@ExceptionHandler(EmailException.class)
	public ModelAndView handle3(RuntimeException e) {
		ModelAndView mav = new ModelAndView("/shop/error/result");
		mav.addObject("e", e);
		return mav;
	}
}
