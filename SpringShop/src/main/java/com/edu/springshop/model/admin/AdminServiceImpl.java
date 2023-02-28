package com.edu.springshop.model.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.springshop.domain.Admin;
import com.edu.springshop.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDAO adminDAO;

	public List selectAll() {
		return adminDAO.selectAll();
	}

	public Admin select(int admin_idx) {
		return adminDAO.select(admin_idx);
	}

	public Admin login(Admin admin) throws AdminException{
		return adminDAO.login(admin);
	}

	public void insert(Admin admin) throws AdminException{
		adminDAO.insert(admin);
	}
	
	
}
