package com.edu.springboard.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.springboard.domain.Notice;
import com.edu.springboard.exception.NoticeException;
import com.edu.springboard.model.notice.NoticeService;

import oracle.jdbc.proxy.annotation.Post;

@RestController
@RequestMapping("/rest")
public class RestNoticeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NoticeService noticeService;

	@PostMapping("/notice/regist")
	public String regist(Notice notice) {

		logger.info(notice.getTitle());
		logger.info(notice.getWriter());
		logger.info(notice.getContent());
		noticeService.insert(notice);
		return "test";
	}

	// 글 목록 요청 처리
	@GetMapping("/notice/list")
	public List<Notice> getList() {

		// 3단계
		List list = noticeService.selectAll();

		// 4단계 : jsp 뷰로 가져갈 일이 없으므로, 4단계 생략

		return list;
	}

	// 상세페이지 요청 처리
	@GetMapping("/notice/detail")
	public Notice getDetail(int notice_idx) {
		// 3단계
		Notice notice = noticeService.select(notice_idx);
		System.out.println(notice);
		return notice;
	}

	// 삭제 요청 처리
	@GetMapping("/notice/delete")
	public String delete(int notice_idx) {
		// 3단계
		noticeService.delete(notice_idx);

		// 반환값을 보다 체계적인 정보를 구성하려면, ResponseEntity라는 객체 이용 가능
		return "ok";
	}

	// 수정 요청 처리
	@PostMapping("/notice/update")
		public String update(Notice notice) {
			
			//3단계
			noticeService.update(notice);
			
			
			return "ok";
		}

	@ExceptionHandler(NoticeException.class)
	public String handle(NoticeException e) {
		return e.getMessage();
	}

}
