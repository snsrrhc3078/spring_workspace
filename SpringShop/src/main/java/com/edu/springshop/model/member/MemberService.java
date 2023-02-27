package com.edu.springshop.model.member;

import java.util.List;

import com.edu.springshop.domain.Member;

public interface MemberService {
	public List selectAll();
	public Member select(int member_idx);
	public Member selectByID(Member member);
	public void regist(Member member);
	public void update(Member member);
	public void unregist(int member_idx);
}
