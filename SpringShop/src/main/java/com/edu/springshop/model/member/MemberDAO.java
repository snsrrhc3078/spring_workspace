package com.edu.springshop.model.member;

import java.util.List;

import com.edu.springshop.domain.Member;

public interface MemberDAO {
	public List selectAll();
	public Member select(int member_idx);
	public Member selectByID(Member member);
	public void insert(Member member);
	public void update(Member member);
	public void delete(int member_idx);
	
}
