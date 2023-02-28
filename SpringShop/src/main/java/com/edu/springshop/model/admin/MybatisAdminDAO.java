package com.edu.springshop.model.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.domain.Admin;
import com.edu.springshop.exception.AdminException;

@Repository
public class MybatisAdminDAO implements AdminDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll() {
		return sqlSessionTemplate.selectList("Admin.selectAll");
	}

	public Admin select(int admin_idx) {
		return sqlSessionTemplate.selectOne("Admin.select", admin_idx);
	}

	public Admin login(Admin admin) throws AdminException{
		Admin result = sqlSessionTemplate.selectOne("Admin.login", admin);
		if (result == null) throw new AdminException("어드민 로그인 실패");
		return result;
	}

	public void insert(Admin admin) throws AdminException{
		int result = sqlSessionTemplate.insert("Admin.insert", admin);
		if(result < 1) throw new AdminException("어드민 등록 실패");
	}
	
	
	
}
