package com.edu.SpringMVC1.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.edu.SpringMVC1.domain.Board;
import com.edu.SpringMVC1.exception.BoardException;



public class BoardDAO {
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<Board> selectAll(){
		return sqlSession.selectList("Board.selectAll");
	}
	public Board select(int board_idx){
		return sqlSession.selectOne("Board.select", board_idx);
	}
	
	public void insert(Board board) throws BoardException{
		int result = sqlSession.insert("Board.insert", board);
		if(result<1) {
			throw new BoardException("등록실패");
		}
	}
	public void update(Board board) throws BoardException{
		int result = sqlSession.update("Board.update", board);
		if(result<1) {
			throw new BoardException("수정실패");
		}
	}
	public void delete(int board_idx) throws BoardException{
		int result = sqlSession.delete("Board.delete", board_idx);
		if(result<1) {
			throw new BoardException("삭제실패");
		}
	}

	

}
