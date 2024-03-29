package com.edu.SpringMVC1.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//jdk 5 부터 지원되는 주석 중의 하나로, 우리가 알고 있는 일반적인
//주석과는 달리, 프로그램에서 사용되는 주석
@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	//CRUD를 이 클래스에서 모두 처리할 수 있다
	//ListController, DetailController... 등등 따로 만들 필요 없다
	
	//이 메서드가 어떤 uri를 처리할지 uri매핑
	@RequestMapping("/board/list")
	public String getList() {
		logger.info("게시판 목록을 처리할 예정");
		return "board/board_list";
	}
}
