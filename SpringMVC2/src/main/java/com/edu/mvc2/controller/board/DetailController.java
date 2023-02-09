package com.edu.mvc2.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.edu.mvc2.domain.Board;
import com.edu.mvc2.model.board.BoardService;

import lombok.Setter;
//디테일 담당할 하위 컨트롤러
@Setter
public class DetailController implements Controller{
	private BoardService boardService;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String board_idx = request.getParameter("board_idx");
		
		//3단계: 일 시키기
		Board board = boardService.select(Integer.parseInt(board_idx));
		
		//4단계: 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("/board/detail");
		return mav;
	}

}
