package com.edu.springboard.client.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springboard.domain.Gallery;
import com.edu.springboard.exception.FileUploadException;
import com.edu.springboard.exception.GalleryException;
import com.edu.springboard.exception.PhotoException;
import com.edu.springboard.model.gallery.GalleryService;

//갤러리와 관련된 모든 요청을 처리하는 하위 컨트롤러
@Controller
public class GalleryController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private GalleryService galleryService;
	
	@GetMapping("/gallery/registform")
	public ModelAndView registform() {
		return new ModelAndView("/gallery/regist");
	}
	
	
	
	
	
	
}
