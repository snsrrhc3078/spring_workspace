package com.edu.springboard.client.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springboard.domain.Notice;
import com.edu.springboard.model.notice.NoticeService;

@RestController
@RequestMapping("/rest")
public class RestApiNoticeController {
	//REST를 준수하여 URL을 매핑해보자 (RESTful)
	
	@Autowired 
	private NoticeService noticeService;
	
	
	
	//목록 요청
	@GetMapping("/notices")
	public List<Notice> selectAll() {
		//3단계
		List list = noticeService.selectAll();
		
		return list;
	}
	
	//상세 보기
	@GetMapping("/notices/{notice_idx}")
	public Notice select(@PathVariable("notice_idx") int notice_idx) {
		Notice notice = null;
		
		//3단계
		notice = noticeService.select(notice_idx);
		
		return notice;
	}
	
	//삭제 요청
	@DeleteMapping("/notices/{notice_idx}")
	public String delete(@PathVariable("notice_idx") int notice_idx) {
		noticeService.delete(notice_idx);
		
		return "ok";
	}
	
	//수정 요청
	@PutMapping("/notices")
	public String edit(Notice notice) {
		
		//3단계
		noticeService.update(notice);
		
		return "ok";
	}
	
	//등록 요청
	@PostMapping("/notices")
	public String regist(Notice notice) {
		
		//3단계
		noticeService.insert(notice);
		
		return "ok";
	}
}
