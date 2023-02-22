package com.edu.springshop.model.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.springshop.domain.Category;

@Repository
public class JdbcCategoryDAO implements CategoryDAO{

	public List selectAll() {
		return null;
	}

	public Category select(int category_idx) {
		return null;
	}

	public void insert(Category category) {
		
	}

	public void update(Category category) {
		
	}

	public void delete(int category_idx) {
		
	}

}
