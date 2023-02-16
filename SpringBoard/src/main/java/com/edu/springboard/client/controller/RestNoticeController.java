package com.edu.springboard.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springboard.domain.Notice;
import com.edu.springboard.exception.NoticeException;
import com.edu.springboard.model.notice.NoticeService;

@RestController
public class RestNoticeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping("/rest/notice/regist")
	public String regist(Notice notice) {
		
		logger.info(notice.getTitle());
		logger.info(notice.getWriter());
		logger.info(notice.getContent());
		//noticeService.insert(notice);
		return "test";
	}
	
	@ExceptionHandler(NoticeException.class)
	public String handle(NoticeException e) {
		return "에러났음";
	}
}
