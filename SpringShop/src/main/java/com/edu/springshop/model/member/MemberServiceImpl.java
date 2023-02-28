package com.edu.springshop.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.springshop.domain.Member;
import com.edu.springshop.exception.EmailException;
import com.edu.springshop.exception.HashException;
import com.edu.springshop.exception.MemberException;
import com.edu.springshop.util.EmailManager;
import com.edu.springshop.util.PassConverter;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PassConverter passConverter;
	
	@Autowired
	private EmailManager emailManager;
	
	public List selectAll() {
		return memberDAO.selectAll();
	}

	public Member select(int member_idx) {
		return memberDAO.select(member_idx);
	}

	public Member selectByID(Member member) {
		return memberDAO.selectByID(member);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Member member) throws HashException, EmailException, MemberException{
		//암호화
		//DTO의 pass를 해시값으로 대체
		member.setPass(passConverter.convertHash(member.getPass()));
		
		//이메일
		emailManager.send(member.getEmail());
		
		//등록
		memberDAO.insert(member);
	}

	public void update(Member member) {
		
	}

	public void unregist(int member_idx) {
		
	}
	
}
