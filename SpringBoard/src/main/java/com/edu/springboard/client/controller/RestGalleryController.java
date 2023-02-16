package com.edu.springboard.client.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springboard.domain.Gallery;
import com.edu.springboard.exception.FileUploadException;
import com.edu.springboard.exception.GalleryException;
import com.edu.springboard.exception.PhotoException;
import com.edu.springboard.model.gallery.GalleryService;
import com.google.gson.Gson;

//RestController일 경우, 모든 메서드에 @ResponseBody생략 가능
@RestController
public class RestGalleryController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private GalleryService galleryService;
	

	// 갤러리 업로드 요청 처리
	@PostMapping("/gallery/regist")
	//@ResponseBody // 메서드의 반환값을 jsp로 매핑하지말고, 순수 데이터로 처리
	// 응답정보를 보낸다
	public String regist(Gallery gallery, HttpServletRequest request) {
//			MultipartFile[] files = gallery.getPhoto();		
//			for(int i =0;i<files.length;i++) {
//				logger.info("업로드된 파일은 " + files[i].getOriginalFilename());
//			}

		ServletContext application = request.getSession().getServletContext();
		String realPath = application.getRealPath(application.getInitParameter("dataPath"));
		// 3단계: 글 등록하기(db + file)
		galleryService.regist(gallery, realPath);

		return "ok";
	}
	
	//비동기 목록 요청 처리 (주요 클라이언트 - web - ajax)
	//앱 (안드로이드, 아이폰)
	@GetMapping("/rest/gallery/list")
	public List getList() {
		
		//3단계
		List<Gallery> list = galleryService.selectAll();
		Gson gson = new Gson();
		
		return list;
	}
	
	//갤러리 한건 가져오기
	@GetMapping("/rest/gallery/detail")
	public Gallery getDetail(int gallery_idx) {
		Gallery gallery = galleryService.select(gallery_idx);
		System.out.println(gallery);
		return gallery;
	}

	// 컨트롤러 메서드들에서 예외가 발생했을때의 처리
	@ExceptionHandler(FileUploadException.class)
	public String handle3(GalleryException e) {
		return "에러남";
	}

	@ExceptionHandler(GalleryException.class)
	public String handle(GalleryException e) {
		return "에러남";
	}

	@ExceptionHandler(PhotoException.class)
	public String handle2(GalleryException e) {
		return "에러남";
	}
}
