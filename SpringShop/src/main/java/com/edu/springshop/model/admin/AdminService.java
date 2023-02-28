package com.edu.springshop.model.admin;

import java.util.List;

import com.edu.springshop.domain.Admin;

public interface AdminService {
	public List selectAll();
	public Admin select(int admin_idx);
	public Admin login(Admin admin);
	public void insert(Admin admin);
}
