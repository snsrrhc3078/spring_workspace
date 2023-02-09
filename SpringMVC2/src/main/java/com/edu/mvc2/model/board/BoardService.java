package com.edu.mvc2.model.board;

import java.util.List;

import com.edu.mvc2.domain.Board;

public interface BoardService {
	
	public List selectAll();
	public Board select(int board_idx);
	public void regist(Board board);
	public void update(Board board);
	public void delete(int board_idx);
}
