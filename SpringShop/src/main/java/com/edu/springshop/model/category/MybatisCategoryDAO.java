package com.edu.springshop.model.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.domain.Category;
import com.edu.springshop.exception.CategoryException;

@Repository
public class MybatisCategoryDAO implements CategoryDAO {

	//1순위 id와 변수명 비교, 2순위 자료형 비교
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll() {
		return sqlSessionTemplate.selectList("Category.selectAll");
	}

	public Category select(int category_idx) {
		return sqlSessionTemplate.selectOne("Category.select", category_idx);
	}

	public void insert(Category category) throws CategoryException{
		int result = sqlSessionTemplate.insert("Category.insert", category);
		if(result <1) {
			throw new CategoryException("카테고리 등록 실패");
		}
	}

	public void update(Category category) throws CategoryException{
		int result = sqlSessionTemplate.update("Category.update", category);
		if(result <1) {
			throw new CategoryException("카테고리 수정 실패");
		}
	}

	public void delete(int category_idx) throws CategoryException{
		int result = sqlSessionTemplate.delete("Category.delete", category_idx);
		if(result <1) {
			throw new CategoryException("카테고리 삭제 실패");
		}
	}

}
