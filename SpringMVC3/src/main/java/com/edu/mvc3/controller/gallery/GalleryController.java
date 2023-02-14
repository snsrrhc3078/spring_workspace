package com.edu.mvc3.controller.gallery;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edu.mvc3.domain.Gallery;
import com.edu.mvc3.exception.UploadException;
import com.edu.mvc3.model.GalleryService;
import com.edu.mvc3.model.util.FileManager;

import lombok.Setter;


//갤러리와 관련된 모든 요청을 처리할 수 있는 하위 컨트롤러
//POJO 추구: 즉 개발자에게 구현을 강제하지 않는다
//더이상 xml에 등록하지 않으므로, 컨트롤러 클래스에 무언가를 표시해야
//스프링이 인스턴스를 생성해준다
//특히 스프링이 생성할 인스턴스의 대상이 되는 객체들을 컴포넌트라 하는데
//이 컴포넌트의 대표적인 종류로는 @Controller, @Service
//@Repository, @Component
@Controller
@Setter
public class GalleryController {
	Logger logger = LoggerFactory.getLogger(GalleryController.class);
	
	//스프링의 빈 컨테이너가 보유한 빈들 중, 아래의 멤버변수명이나
	//자료형과 일치하는 빈이 있을 경우 자동으로 setter 주입시켜준다
	@Autowired private FileManager fm;
	@Autowired private GalleryService galleryService;
	
	//글의 목록 요청 처리
	//url 매핑도 xml이 아닌 자바클래스에 둔다
	@RequestMapping(value="/client/gallery/list")
	public String getList() {
		logger.info("갤러리 목록 요청받음");
		//비록 string을 반환하지만, 여전히 ViewResolver의 영향을 받는다
		return "gallery/list";
	}
	
	//갤러리 등록 폼 요청 처리
	@RequestMapping(value = "/client/gallery/registform")
	public String getRegistForm() {
		return "gallery/regist";
	}
	
	//갤러리 등록 요청
	@RequestMapping(value = "/client/galley/regist")
	public String regist(Gallery gallery, HttpServletRequest request) {
		
		MultipartFile file = gallery.getFile();
		
		ServletContext context = request.getSession().getServletContext();
		String realPath = (String)context.getAttribute("dataPath");
		
		//현재 업로드된 파일은 아직 물리적 파일로 존재하지 않고 메모리상에 존재하므로
		//서버의 로컬 디렉토리에 저장 해보자
		fm.saveFile(file, realPath); //여기서 exception 발생하면 아래 ExceptionHandler의 handle() 메서드가 호출된다
	
		//글쓴 후, 재접속을 유도한다
		return "redirect:/client/gallery/list";
	}
	
	//현재 이 컨트롤러의 모든 요청처리 메서드에서, 예외가 발생하면 아래의
	//ExceptionHander 가 동작하게 되어 있는데, 단 해당 예외클래스에 맞는 메서드만
	//호출됨
	@ExceptionHandler(UploadException.class)
	public ModelAndView handle(UploadException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error/result");
		mav.addObject("e", e); //에러객체 저장하기
		//개발자가 redirect를 명시하지 않았으므로 error.jsp로 foward 된다
		//redirect를 명시하지 않은이유: 결과값이 존재하므로
		return mav;
	}
}
