package com.edu.springshop.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.springshop.domain.Cart;
import com.edu.springshop.exception.CartException;

@Repository
public class MybatisCartDAO implements CartDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll(Cart cart) {
		return sqlSessionTemplate.selectList("Cart.selectAll", cart);
	}

	public void insert(Cart cart) throws CartException{
		int result = sqlSessionTemplate.insert("Cart.insert", cart);
		if(result < 1) {
			throw new CartException("장바구니 등록 실패");
		}
	}

	public int selectCount(Cart cart) {
		return sqlSessionTemplate.selectOne("Cart.selectCount", cart);
	}

	public void updateEa(Cart cart) throws CartException{
		int result = sqlSessionTemplate.update("Cart.updateEa", cart);
		if(result < 1) {
			throw new CartException("장바구니 EA 수정 실패");
		}
	}

}
