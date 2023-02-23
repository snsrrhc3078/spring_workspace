package com.edu.springshop.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.domain.Product;
import com.edu.springshop.exception.ProductException;

@Repository
public class MybatisProductDAO implements ProductDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<Product> selectAll() {
		return sqlSessionTemplate.selectList("Product.selectAll");
	}

	public Product select(int product_idx) {
		return sqlSessionTemplate.selectOne("Product.select", product_idx);
	}

	public void insert(Product product) throws ProductException{
		int result = sqlSessionTemplate.insert("Product.insert", product);
		if(result < 1) {
			throw new ProductException("상품 등록 실패");
		}
	}

	public void update(Product product) throws ProductException{
		int result = sqlSessionTemplate.update("Product.update", product);
		if(result < 1) {
			throw new ProductException("상품 수정 실패");
		}
	}

	public void delete(int product_idx) throws ProductException{
		int result = sqlSessionTemplate.delete("Product.delete", product_idx);
		if(result < 1) {
			throw new ProductException("상품 삭제 실패");
		}
	}

}
