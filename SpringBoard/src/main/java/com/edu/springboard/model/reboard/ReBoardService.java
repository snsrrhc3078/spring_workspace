package com.edu.springboard.model.reboard;

import java.util.List;

import com.edu.springboard.domain.ReBoard;

public interface ReBoardService {
	//DAO와 service는 메서드명이 같아야 한다는 기준 없다!
	//하지만 업무내용이 동일한데 굳이, 다르게 줄 이유도 없다
	public List selectAll();
	public ReBoard select(int reboard_idx);
	public void insert(ReBoard reboard);
	public void update(ReBoard reboard);
	public void delete(int reboard_idx);
	
	//답변관련
	//서비스에서 하나로 두가지동작을 엮음
	public void registReply(ReBoard reboard); //답변등록
	
}
